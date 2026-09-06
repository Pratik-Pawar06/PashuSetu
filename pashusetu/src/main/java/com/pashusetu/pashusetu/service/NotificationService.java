package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification createNotification(Notification notification);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByUserId(Long userId);

    List<Notification> getUnreadNotifications(Long userId);

    void markAsRead(Long id);

    void deleteNotification(Long id);
}