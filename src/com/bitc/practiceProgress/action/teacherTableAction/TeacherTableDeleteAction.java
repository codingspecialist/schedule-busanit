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
import com.bitc.practiceProgress.dto.PracticeProgressDto;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.repository.PracticeTableRepository;
import com.bitc.practiceProgress.repository.TeacherTableRepository;
import com.bitc.practiceProgress.util.Script;

public class TeacherTableDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		TeacherTableRepository repository = TeacherTableRepository.getInstance();
		int result = repository.delete(id);
		
		if(result == 1) {
			Script.href("삭제 성공", "/busanit/teachertable?cmd=input", response);
		}else {
			Script.back("삭제에 실패하였습니다", response);
		}
	}
}

