package com.magento2omicron.packages.models;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

import javax.annotation.Nullable;

public class Notifications {

    /**
     * Notifies Error
     * @param project Project
     * @param content String
     */
    public static void notifyError(@Nullable Project project, String content) {
        NotificationGroupManager.getInstance().getNotificationGroup("Magento 2 Omicron Notification Group")
                .createNotification(content, NotificationType.ERROR)
                .notify(project);
    }

    /**
     * Notifies Information
     * @param project Project
     * @param content String
     */
    public static void notifyInformation(@Nullable Project project, String content) {
        NotificationGroupManager.getInstance().getNotificationGroup("Magento 2 Omicron Notification Group")
                .createNotification(content, NotificationType.INFORMATION)
                .notify(project);
    }

    /**
     * Notifies Warning
     * @param project Project
     * @param content String
     */
    public static void notifyWarning(@Nullable Project project, String content) {
        NotificationGroupManager.getInstance().getNotificationGroup("Magento 2 Omicron Notification Group")
                .createNotification(content, NotificationType.WARNING)
                .notify(project);
    }
}
