package com.project.decrypt.dao;

import com.project.decrypt.model.Level;
import com.project.decrypt.model.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
    Optional<UserAchievement> findByUser(Long userId);
}