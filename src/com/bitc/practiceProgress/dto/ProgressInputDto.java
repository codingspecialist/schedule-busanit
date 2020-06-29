package com.bitc.practiceProgress.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressInputDto {
	int room;
	String className;
	String homeroomProf;
}
