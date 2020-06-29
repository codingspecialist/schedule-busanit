package com.bitc.practiceProgress.action.classTableAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.model.ClassTable;
import com.bitc.practiceProgress.repository.ClassTableRepository;

public class ClassTableDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int room = Integer.parseInt(request.getParameter("room"));
		
		ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
		
		ClassTable classTable = classTableRepository.findByRoom(room);
		
		String processCode;
		
		if(classTable.getClassPart() == null ||
				classTable.getClassPart().equals("") ||
				classTable.getClassOpen() == null ||
				classTable.getClassOpen().equals("")) {
			
			processCode = "정보가 충분하지 않습니다.";
			
		} else {
			
			String codeId = classTable.getId()+10000+"";
			
			processCode = classTable.getClassPart()
					+classTable.getClassOpen().replace("-", "")
					+codeId.substring(codeId.length()-4, codeId.length());
			
		}
		
		
		request.setAttribute("processCode", processCode);
		request.setAttribute("classTable", classTable);
		
		RequestDispatcher rd = request.getRequestDispatcher("/input/viewclassdetail.jsp");
		rd.forward(request, response);
		
	}

}
