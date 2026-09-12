package com.pashusetu.pashusetu.service.impl;

import com.pashusetu.pashusetu.entity.Notification;
import com.pashusetu.pashusetu.repository.NotificationRepository;
import com.pashusetu.pashusetu.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository) {

        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification createNotification(Notification notification) {

        notification.setCreatedAt(LocalDateTime.now());

        boolean exists = notificationRepository.existsByUserIdAndMessage(
                notification.getUser().getId(),
                notification.getMessage()
        );

        if (exists) {
            return null;
        }

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {

        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getNotificationsByUserId(Long userId) {

        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getUnreadNotifications(Long userId) {

        return notificationRepository
                .findByUserIdAndIsReadFalse(userId);
    }

    @Override
    public void markAsRead(Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found with id: " + id
                                )
                        );

        notification.setRead(true);

        notificationRepository.save(notification);
    }

    @Override
    public void deleteNotification(Long id) {

        Notification notification =
                notificationRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found with id: " + id
                                )
                        );

        notificationRepository.delete(notification);
    }
}