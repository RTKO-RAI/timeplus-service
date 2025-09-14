package com.rbinternational.neura.timeplus.dto.Response;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Leave {
    private Long id;

    private Long employeeId;

    private LeaveType type;

    private LocalDate startDate;

    private LocalDate endDate;

    private LeaveStatus status;
}
