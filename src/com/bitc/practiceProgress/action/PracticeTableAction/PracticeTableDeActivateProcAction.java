package com.bitc.practiceProgress.action.PracticeTableAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.util.Script;

public class PracticeTableDeActivateProcAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		
		int result = classTableRepository.updateStatusFalse(id);
		
		Script.outText(result+"", response);

	}
}
