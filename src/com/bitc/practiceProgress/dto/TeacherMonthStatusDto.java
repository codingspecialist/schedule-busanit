package com.bitc.practiceProgress.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherMonthStatusDto {
	String className;
	List<MonthTime> monthTime;	
}
