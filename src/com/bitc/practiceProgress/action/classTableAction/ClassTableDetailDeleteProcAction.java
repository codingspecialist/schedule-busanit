package com.bitc.practiceProgress.action.classTableAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.repository.ClassTableRepository;
import com.bitc.practiceProgress.repository.PracticeTableRepository;
import com.bitc.practiceProgress.util.Script;

public class ClassTableDetailDeleteProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		PracticeTableRepository practiceTableRepository = PracticeTableRepository.getInstance();
		
		int result = practiceTableRepository.delete(id);
		
		System.out.println("result" +result);
		
		if(result >= 0) {
			
			ClassTableRepository classTableRepository = ClassTableRepository.getInstance();
			
			int classResult = classTableRepository.delete(id);
			
			System.out.println("classResult" +classResult);
			
			if (classResult == 1) {
				Script.putScript("삭제에 성공하였습니다.", "opener.location.reload(); window.close();", response);			
			} else {
				Script.putScript("엑셀 데이터 삭제에 성공하였습니다만, 훈련과정 삭제에 실패하였습니다.", "opener.location.reload(); window.close();", response);
			}
			
		} else {
			Script.putScript("삭제에 실패하였습니다.", "opener.location.reload(); window.close();", response);
		}
		
	}

}
