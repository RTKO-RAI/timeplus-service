package com.rbinternational.neura.timeplus.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectName;

    @OneToMany(mappedBy = "project")
    private Set<TimeRecording> timeRecordingSet;
}
