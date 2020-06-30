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
import com.bitc.practiceProgress.util.Script;

public class TeacherTableInputProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String teacherType = request.getParameter("teacherType");
		String teacherPart = request.getParameter("teacherPart");
		String teacherName = request.getParameter("teacherName");
		
		System.out.println(teacherType);
		System.out.println(teacherPart);
		System.out.println(teacherName);
		
		TeacherTableRepository repository = 
				TeacherTableRepository.getInstance();
		
		TeacherTable teacher = new TeacherTable();
		teacher.setTeacherName(teacherName);
		teacher.setTeacherType(teacherType);
		teacher.setTeacherPart(teacherPart);
		int result = repository.save(teacher);
		
		if(result == 1 ) {
			Script.href("강사 등록이 완료되었습니다", "/busanit/teachertable?cmd=input", response);
		}else {
			Script.back("강사 등록에 실패하였습니다.", response);
		}
	}
}

