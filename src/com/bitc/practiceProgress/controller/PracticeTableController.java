package com.bitc.practiceProgress.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.action.PracticeTableAction.PracticeTableActivateProcAction;
import com.bitc.practiceProgress.action.PracticeTableAction.PracticeTableAddExcelAction;
import com.bitc.practiceProgress.action.PracticeTableAction.PracticeTableAddExcelProcAction;
import com.bitc.practiceProgress.action.PracticeTableAction.PracticeTableChangeExcelAction;
import com.bitc.practiceProgress.action.PracticeTableAction.PracticeTableChangeExcelProcAction;
import com.bitc.practiceProgress.action.PracticeTableAction.PracticeTableDeActivateProcAction;
import com.bitc.practiceProgress.action.PracticeTableAction.PracticeTableInputAction;
import com.bitc.practiceProgress.action.PracticeTableAction.PracticeTableStatisticsAction;

// http://localhost:8000/blog/user
@WebServlet("/practicetable")
public class PracticeTableController extends HttpServlet {
	private final static String TAG = "PracticeTableController : ";
	private static final long serialVersionUID = 1L;

	public PracticeTableController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://localhost:8000/blog/user?cmd=join
		String cmd = request.getParameter("cmd");
		System.out.println(TAG + "router : " + cmd);
		Action action = router(cmd);
		action.execute(request, response);
	}

	public Action router(String cmd) {
		if (cmd.equals("input")) {
			return new PracticeTableInputAction();
		} else if (cmd.equals("activateProc")) {
			return new PracticeTableActivateProcAction();
		} else if (cmd.equals("deActivateProc")) {
			return new PracticeTableDeActivateProcAction();
		} else if (cmd.equals("addExcel")) {
			return new PracticeTableAddExcelAction();
		} else if (cmd.equals("addExcelProc")) {
			return new PracticeTableAddExcelProcAction();
		} else if (cmd.equals("changeExcel")) {
			return new PracticeTableChangeExcelAction();
		} else if (cmd.equals("changeExcelProc")) {
			return new PracticeTableChangeExcelProcAction();
		} else if (cmd.equals("statistics")) {
			return new PracticeTableStatisticsAction();
		}

		return null;
	}

}