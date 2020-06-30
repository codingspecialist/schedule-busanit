package com.bitc.practiceProgress.action.teacherTableAction;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.dto.TeacherMonthStatusDto;
import com.bitc.practiceProgress.model.TeacherTable;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.repository.TeacherTableRepository;

public class TeacherTableStatusAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("MM");
		String currentMonth = formater.format(cal.getTime());
		
		SimpleDateFormat formater2 = new SimpleDateFormat("YYYY");
		String currentYear = formater2.format(cal.getTime());
		
		TeacherTableRepository teacherTableRepository = 
				TeacherTableRepository.getInstance();
		ClassTableRepository classTalbleRepository = 
				ClassTableRepository.getInstance();
		
		List<TeacherMonthStatusDto> dtos = new ArrayList<>();
		List<TeacherTable> teachers = teacherTableRepository.findAllSort();
		
		// 모든 클래스 for 돌리기 findTrueRoomListSort
		List<Integer> classIds = classTalbleRepository.findTrueRoomListSort();
		
		for (Integer classId : classIds) {
			TeacherMonthStatusDto dto = teacherTableRepository.findTime(classId, currentMonth);
			dtos.add(dto);
		}

		List<Integer> monthCount = teacherTableRepository.findMonthCount(currentMonth);
		List<Integer> inTeacherCount = teacherTableRepository.findYearCount(currentYear, "전임강사");
		List<Integer> outTeacherCount = teacherTableRepository.findYearCount(currentYear, "외래강사");
		
		// 소계
		int inAllCount = 0;
		int outAllCount = 0;
		for (Integer i : inTeacherCount) {
			inAllCount = inAllCount + i;
		}
		
		for (Integer i : outTeacherCount) {
			outAllCount = outAllCount + i;
		}
		inTeacherCount.add(inAllCount);
		outTeacherCount.add(outAllCount);
		
		// 비율
		double inRatioTemp = 0;
		double outRatioTemp = 0;
		long inRatio = 0;
		long outRatio = 0;
		inRatioTemp = ((double)inAllCount / (inAllCount+outAllCount))*100;
		outRatioTemp = ((double)outAllCount / (inAllCount+outAllCount))*100;
		inRatio = Math.round(inRatioTemp);
		outRatio = Math.round(outRatioTemp);
		inTeacherCount.add((int)inRatio);
		outTeacherCount.add((int)outRatio);
		
		request.setAttribute("teachers", teachers);
		request.setAttribute("dtos", dtos);
		request.setAttribute("monthCount", monthCount);
		request.setAttribute("inTeacherCount", inTeacherCount);
		request.setAttribute("outTeacherCount", outTeacherCount);
		request.setAttribute("currentYear", currentYear);
		RequestDispatcher dis = request.getRequestDispatcher("/input/monthtime.jsp");
		dis.forward(request, response);
	}
}
