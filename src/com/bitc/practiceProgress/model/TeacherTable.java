package com.bitc.practiceProgress.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherTable {
	
	int id;
	String teacherName;
	String teacherType;
	String teacherPart;
	
}
