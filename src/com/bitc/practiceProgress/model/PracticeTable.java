package com.bitc.practiceProgress.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PracticeTable {
	
	int id;
	String className;
	String classDate;
	String dayWeek;
	int classTime;
	String startTime;
	String endTime;
	String subject1;
	String subject2;
	String prof;
	int room;
	int classId;
	
}
