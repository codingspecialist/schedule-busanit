package com.bitc.practiceProgress.action.classTableAction;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.model.ClassTable;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.util.Script;

public class ClassTableInputProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		
		List<Integer> trueRoomList = classTableRepository.findTrueRoomList();
		
		for (Integer integer : trueRoomList) {
			if(Integer.parseInt(request.getParameter("room")) == integer) {
				Script.getMessage("잘못된 접근입니다.", response);
				return;
			}
		}
		
		ClassTable classTable = ClassTable.builder()
				.room(Integer.parseInt(request.getParameter("room")))
				.className(request.getParameter("className"))
				.classPart(request.getParameter("classPart"))
				.classOpen(request.getParameter("classOpen"))
				.classClose(request.getParameter("classClose"))
				.homeroomProf(request.getParameter("homeroomProf"))
				.build();
		
		int result = classTableRepository.save(classTable);
		
		if(result == 1) {
			
			Script.href("등록에 성공하였습니다.", "/busanit/classtable?cmd=input", response);
			
		} else {
			Script.back("등록에 실패하였습니다.", response);
		}

	}

}
