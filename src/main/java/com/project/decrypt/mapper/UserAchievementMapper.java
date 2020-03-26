package com.project.decrypt.mapper;

import com.project.decrypt.dto.UserAchievementDTO;
import com.project.decrypt.model.UserAchievement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class UserAchievementMapper {
    public abstract UserAchievement toEntity(UserAchievementDTO dto);

    public abstract List<UserAchievement> toEntityList(List<UserAchievementDTO> dtoList);

    public abstract UserAchievementDTO toDto(UserAchievement entity);

    public abstract List<UserAchievementDTO> toDtoList(List<UserAchievement> entityList);
}