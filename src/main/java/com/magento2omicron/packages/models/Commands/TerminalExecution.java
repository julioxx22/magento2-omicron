package com.magento2omicron.packages.models.Commands;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.*;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.notification.*;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.magento2omicron.packages.models.Notifications;
import org.jetbrains.annotations.NotNull;
import java.nio.charset.StandardCharsets;

public class TerminalExecution {

    private final Project project;

    /**
     * TerminalExecution Constructor.
     *
     * @param project Project
     */
    public TerminalExecution(Project project) {
        this.project = project;
    }

    /**
     * Run a Magento CLI Command on the IDE.
     *
     * @param commandDefinition Definition
     * @param canBeCancelled boolean
     */
    public void execute(Definition commandDefinition, boolean canBeCancelled) {

        try {
            ProgressManager.getInstance().run(new Task.Modal(project, commandDefinition.getTitle(), canBeCancelled){

                @Override
                public void run(@NotNull ProgressIndicator indicator) {

                    indicator.setIndeterminate(false);
                    GeneralCommandLine generalCommandLine = new GeneralCommandLine(commandDefinition.getCommands());
                    generalCommandLine.setCharset(StandardCharsets.UTF_8);
                    generalCommandLine.setWorkDirectory(project.getBasePath());

                    ProcessHandler processHandler;
                    try {
                        processHandler = new OSProcessHandler(generalCommandLine);
                        final ConsoleView consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
                        processHandler.addProcessListener(new ProcessAdapter() {

                            String lastCommand = "";

                            @Override
                            public void startNotified(@NotNull ProcessEvent event) {
                                super.startNotified(event);
                                Notifications.notifyInformation(project, "Magento 2 Omicron | " + commandDefinition.getTitle() + " Initiated");
                            }

                            @Override
                            public void processTerminated(@NotNull final ProcessEvent event) {
                                if (event.getExitCode() != 0) {
                                    Notifications.notifyError(project, "Magento 2 Omicron | " + commandDefinition.getTitle() + "Error when trying to complete command: " + lastCommand);
                                } else {
                                    Notifications.notifyInformation(project, "Magento 2 Omicron | " + "Finished successfully.");
                                }
                            }

                            @Override
                            public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
                                super.onTextAvailable(event, outputType);
                                if(!event.getText().replace(" ", "").replace("\n", "").isEmpty()) {
                                    lastCommand = event.getText().trim();
                                    Notifications.notifyInformation(project, "Magento 2 Omicron | " + commandDefinition.getTitle() + ": " + lastCommand);
                                }
                            }
                        });
                        consoleView.attachToProcess(processHandler);
                        processHandler.startNotify();
                        consoleView.print("ls -al", ConsoleViewContentType.NORMAL_OUTPUT);
                    } catch (ExecutionException executionException) {
                        Notifications.notifyError(project, "Magento 2 Omicron | " + commandDefinition.getTitle() + " " + executionException.getMessage());
                    }
                }
            });
        } catch (Exception ioException) {
            Notifications.notifyError(project, "Magento 2 Omicron | " + commandDefinition.getTitle() + " " + ioException.getMessage());
        }
    }
}
