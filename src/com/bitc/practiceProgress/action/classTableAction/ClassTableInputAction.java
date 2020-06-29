package com.bitc.practiceProgress.action.classTableAction;

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

public class ClassTableInputAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		PracticeTableRepository practiceTableRepository = PracticeTableRepository.getInstance();
		
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String classDate = formater.format(cal.getTime());
		
		
		SimpleDateFormat formater1 = new SimpleDateFormat("HHmm");
		String classTimeString = formater1.format(cal.getTime());
		
		int classTime = 0;
		
		if(Integer.parseInt(classTimeString) >= 900 && Integer.parseInt(classTimeString) < 1000) {
			classTime = 1;
		} else if(Integer.parseInt(classTimeString) >= 1000 && Integer.parseInt(classTimeString) < 1100) {
			classTime = 2;
		} else if(Integer.parseInt(classTimeString) >= 1100 && Integer.parseInt(classTimeString) < 1200) {
			classTime = 3;
		} else if(Integer.parseInt(classTimeString) >= 1200 && Integer.parseInt(classTimeString) < 1340) {
			classTime = 4;
		} else if(Integer.parseInt(classTimeString) >= 1340 && Integer.parseInt(classTimeString) < 1440) {
			classTime = 5;
		} else if(Integer.parseInt(classTimeString) >= 1440 && Integer.parseInt(classTimeString) < 1540) {
			classTime = 6;
		} else if(Integer.parseInt(classTimeString) >= 1540 && Integer.parseInt(classTimeString) < 1640) {
			classTime = 7;
		} else if(Integer.parseInt(classTimeString) >= 1640 && Integer.parseInt(classTimeString) < 1740) {
			classTime = 8;
		}
		
		List<Integer> idList = classTableRepository.findIdList();
		
		List<ProgressInputDto> pids = classTableRepository.findClassNameHomeroomProf();
		List<PracticeProgressDto> ppds = practiceTableRepository.findPracticeNow(classTime, classDate, idList);
		
		request.setAttribute("pids", pids);
		request.setAttribute("ppds", ppds);
		
		RequestDispatcher dis = request.getRequestDispatcher("/input/inputprogress.jsp");
		dis.forward(request, response);
	}
}

