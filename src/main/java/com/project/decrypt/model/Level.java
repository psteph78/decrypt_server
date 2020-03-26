package com.project.decrypt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "levels")
public class Level {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer level;
    @Column
    @Enumerated(EnumType.STRING)
    private LevelDiffculty difficulty;
    @Column
    private Double points;
    @Column
    private String solution;
    @Column
    private String hint;
    @Column
    private String problem;
}
