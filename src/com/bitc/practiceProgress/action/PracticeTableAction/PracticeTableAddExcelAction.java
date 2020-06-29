package com.bitc.practiceProgress.action.PracticeTableAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;

public class PracticeTableAddExcelAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("/input/addexcel.jsp");
		rd.forward(request, response);
		
	}

}
