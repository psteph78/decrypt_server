package com.project.decrypt.dto;

import com.project.decrypt.model.LevelDiffculty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelDTO {
    private Long id;
    private Integer level;
    private LevelDiffculty diffculty;
    private Double points;
    private String solution;
    private String hint;
}
