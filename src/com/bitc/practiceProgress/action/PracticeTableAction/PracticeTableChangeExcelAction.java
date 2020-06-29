package com.bitc.practiceProgress.action.PracticeTableAction;

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
import com.bitc.practiceProgress.dto.PracticeProgressDto;
import com.bitc.practiceProgress.model.ClassTable;
import com.bitc.practiceProgress.model.PracticeTable;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.repository.PracticeTableRepository;

public class PracticeTableChangeExcelAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		PracticeTableRepository practiceTableRepository = PracticeTableRepository.getInstance();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		String classDate = formater.format(cal.getTime());
		
		ClassTable classTable = classTableRepository.findById(id);
		
		List<PracticeTable> pts = practiceTableRepository.findPracticeTodayByClassId(classDate, id);
		
		request.setAttribute("id", id);
		request.setAttribute("classTable", classTable);
		request.setAttribute("pts", pts);
		
		RequestDispatcher rd = request.getRequestDispatcher("/input/changeexcel.jsp");
		rd.forward(request, response);
		
	}

}
