package com.rbinternational.neura.timeplus.request;

import com.rbinternational.neura.timeplus.dto.Employee;
import com.rbinternational.neura.timeplus.dto.Project;

import java.time.LocalDate;

public record InsertTimeRecordingRequest(
        LocalDate date,
        double workedHours
) {
}
