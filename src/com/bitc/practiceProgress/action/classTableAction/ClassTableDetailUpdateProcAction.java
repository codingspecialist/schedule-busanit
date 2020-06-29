package com.bitc.practiceProgress.action.classTableAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.model.ClassTable;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.util.Script;

public class ClassTableDetailUpdateProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int room = Integer.parseInt(request.getParameter("room"));
		
		ClassTable classTable = ClassTable.builder()
				.id(Integer.parseInt(request.getParameter("id")))
				.className(request.getParameter("className"))
				.classPart(request.getParameter("classPart"))
				.classOpen(request.getParameter("classOpen"))
				.classClose(request.getParameter("classClose"))
				.homeroomProf(request.getParameter("homeroomProf"))
				.excelName(request.getParameter("excelName"))
				.status(request.getParameter("status"))
				.build();
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		
		int result = classTableRepository.update(classTable);
		
		if(result == 1) {
			
			Script.putScript("변경에 성공하였습니다.", "opener.location.reload(); window.close();", response);
			
		} else {
			Script.getMessage("변경에 실패하였습니다.", response);
		}

	}

}
