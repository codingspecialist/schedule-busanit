package com.bitc.practiceProgress.action.PracticeTableAction;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.util.Script;

public class PracticeTableActivateProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		
		List<Integer> trueRoomList = classTableRepository.findTrueRoomList();
		
		for (Integer integer : trueRoomList) {
			if(Integer.parseInt(request.getParameter("room")) == integer) {
				Script.outText(2+"", response);
				return;
			}
		}
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		int result = classTableRepository.updateStatusTrue(id);
		
		Script.outText(result+"", response);
		
	}

}
