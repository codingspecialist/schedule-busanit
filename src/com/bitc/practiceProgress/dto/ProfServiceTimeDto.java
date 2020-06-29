package com.bitc.practiceProgress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfServiceTimeDto {
	String prof;
	int serviceTime;
}
