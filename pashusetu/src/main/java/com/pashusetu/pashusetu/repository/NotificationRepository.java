package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findByUserIdAndIsReadFalse(Long userId);

    boolean existsByUserIdAndMessage(Long userId, String message);
}