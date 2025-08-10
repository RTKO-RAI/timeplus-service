package com.rbinternational.neura.timeplus;

import com.rbinternational.neura.timeplus.service.TimeRecordingService;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TimeplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeplusApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider tools(TimeRecordingService timeRecordingService) {
		return MethodToolCallbackProvider.builder()
				.toolObjects(timeRecordingService)
				.build();
	}
}
