package com.bitc.practiceProgress.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassTable {
	int id;
	int room;
	String className;
	String classPart;
	String classOpen;
	String classClose;
	String homeroomProf;
	String excelName;
	String status;
}
