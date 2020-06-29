package com.bitc.practiceProgress.action.PracticeTableAction;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.dto.ProfServiceTimeDto;
import com.bitc.practiceProgress.repository.PracticeTableRepository;

public class PracticeTableStatisticsAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int room = Integer.parseInt(request.getParameter("room"));
		String classOpen = request.getParameter("classOpen");
		String classClose = request.getParameter("classClose");
		
		System.out.println(classClose);
		
		PracticeTableRepository practiceTableRepository = PracticeTableRepository.getInstance();
		
		
		int allServiceTime = practiceTableRepository.allServiceTime(id, room);
		List<ProfServiceTimeDto> profServiceTimeDtos = practiceTableRepository.profServiceTime(id, room);
		
		int countWrongClassDate = practiceTableRepository.countWrongClassDate(classOpen, classClose);
		
		boolean noWrongClassDate = false;
		
		if(countWrongClassDate == 0) {
			noWrongClassDate = true;
		}
		
		int sumServiceTime = 0;
		
		for (ProfServiceTimeDto profServiceTimeDto : profServiceTimeDtos) {
			sumServiceTime = sumServiceTime + profServiceTimeDto.getServiceTime();
		}
		
		boolean sameServiceTime = false;
		
		if(allServiceTime == sumServiceTime) {
			sameServiceTime = true;
		}
		
		request.setAttribute("allServiceTime", allServiceTime);
		request.setAttribute("sameServiceTime", sameServiceTime);
		request.setAttribute("noWrongClassDate", noWrongClassDate);	
		request.setAttribute("countWrongClassDate", countWrongClassDate);
		request.setAttribute("profServiceTimeDtos", profServiceTimeDtos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/input/statistics.jsp");
		rd.forward(request, response);
		
	}

}
