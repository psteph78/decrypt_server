package com.project.decrypt.mapper;

import com.project.decrypt.dto.LevelDTO;
import com.project.decrypt.model.Level;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class LevelMapper {
    public abstract Level toEntity(LevelDTO dto);

    public abstract List<Level> toEntityList(List<LevelDTO> dtoList);

    public abstract LevelDTO toDto(Level entity);

    public abstract List<LevelDTO> toDtoList(List<Level> entityList);
}
