package com.magento2omicron.packages.models.PHP;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.vfs.VirtualFile;
import com.magento2omicron.util.Files;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilesUtil {

    /**
     * Returns the collection of virtual files from an action event.
     *
     * @param actionEvent AnActionEvent
     * @return List<VirtualFile>
     */
    public static List<VirtualFile> getFiles(AnActionEvent actionEvent) {
        return Files.getFiles(actionEvent).stream().filter(
                file -> Objects.equals(file.getExtension(), "php")
        ).collect(Collectors.toList());
    }

}
