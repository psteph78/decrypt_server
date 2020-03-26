package com.project.decrypt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAchievementDTO {
    private Long id;
    private Integer user;
    private Integer levelReached;
}
