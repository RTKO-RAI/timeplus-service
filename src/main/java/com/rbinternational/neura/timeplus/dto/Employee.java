package com.rbinternational.neura.timeplus.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private LocalTime insertedTime;

    @OneToMany(mappedBy = "employee")
    private Set<TimeRecording> timeRecordingSet;
}
