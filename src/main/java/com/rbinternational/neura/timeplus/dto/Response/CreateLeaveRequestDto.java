package com.rbinternational.neura.timeplus.dto.Response;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CreateLeaveRequestDto {
    @NotNull(message = "Employee ID must not be null")
    private Long employeeId;

    @NotNull(message = "Leave type must be specified")
    private LeaveType type;

    @NotNull(message = "Start date must not be null")
    private LocalDate startDate;

    @NotNull(message = "End date must not be null")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;
}
