package com.bitc.practiceProgress.action.teacherTableAction;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.dto.ProgressInputDto;
import com.bitc.practiceProgress.model.TeacherTable;
import com.bitc.practiceProgress.dto.PracticeProgressDto;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.repository.PracticeTableRepository;
import com.bitc.practiceProgress.repository.TeacherTableRepository;

public class TeacherTableInputAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TeacherTableRepository repository = TeacherTableRepository.getInstance();
		List<TeacherTable> teachers = repository.findAll();
		
		request.setAttribute("teachers", teachers);
		RequestDispatcher dis = request.getRequestDispatcher("/input/inputteacher.jsp");
		dis.forward(request, response);
	}
}

