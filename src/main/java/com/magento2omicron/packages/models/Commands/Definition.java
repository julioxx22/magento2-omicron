package com.magento2omicron.packages.models.Commands;

import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.magento2omicron.util.Resources;
import org.intellij.sdk.settings.AppSettingsState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class Definition {

    private AppSettingsState mySettingsComponent = new AppSettingsState();
    private String title;
    private final ArrayList<String> commands = new ArrayList<>();

    public Definition(String commandId, Project project) {
        XmlFile commandsMapping = Resources.getInstance("/magento2omicron/", "magento_commands.xml", project).getXmlFile();
        for (XmlTag commandDefinition : new LinkedList<>(Arrays.asList(Objects.requireNonNull(commandsMapping.getRootTag()).findSubTags("command")))) {
            String id = Objects.requireNonNull(commandDefinition.getAttribute("id")).getValue();
            assert id != null;
            if (id.equals(commandId)) {
                this.title = Objects.requireNonNull(commandDefinition.getAttribute("title")).getValue();
                this.commands.add(this.mySettingsComponent.binMagentoExecutable);
                this.commands.addAll(Arrays.asList(
                        Objects.requireNonNull(Objects.requireNonNull(commandDefinition.getAttribute("execution")).getValue()).split(" ")
                ));
            }
        }

    }

    /**
     * Return the current command title, defined in magento_commands.xml
     *
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Return the current command title, defined in magento_commands.xml
     *
     * @return String
     */
    public ArrayList<String> getCommands() {
        return this.commands;
    }
}
