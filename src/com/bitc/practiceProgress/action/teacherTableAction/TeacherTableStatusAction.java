package com.bitc.practiceProgress.action.teacherTableAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.dto.TeacherMonthStatusDto;
import com.bitc.practiceProgress.repository.TeacherTableRepository;
import com.google.gson.Gson;

public class TeacherTableStatusAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherTableRepository repository = 
				TeacherTableRepository.getInstance();
		// 모든 클래스 for 돌리기
		TeacherMonthStatusDto dto = repository.findTime(2, "06");
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(dto);
		System.out.println(jsonData);
	}
}
