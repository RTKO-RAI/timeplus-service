package com.rbinternational.neura.timeplus.client;

import com.rbinternational.neura.timeplus.config.FeignConfig;
import com.rbinternational.neura.timeplus.dto.Response.CreateLeaveRequestDto;
import com.rbinternational.neura.timeplus.dto.Response.Leave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "hr-service",
        url = "${hr.service.url}",
        configuration = {FeignConfig.class})
public interface HrServiceClient {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Leave> createLeave(@RequestBody CreateLeaveRequestDto dto) ;

    @PutMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
    Leave updateLeaveStatus(@PathVariable("id") Long id, @RequestBody Map<String, String> body);

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Leave> getAllLeaves();

    @GetMapping(value = "/employee/{employeeId}/remaining", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Integer> getRemainingDays(@PathVariable("employeeId") Long employeeId);
}
