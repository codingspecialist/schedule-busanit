package com.bitc.practiceProgress.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.action.teacherTableAction.TeacherTableInputAction;
import com.bitc.practiceProgress.action.teacherTableAction.TeacherTableInputProcAction;
import com.bitc.practiceProgress.action.teacherTableAction.TeacherTableStatusAction;

// http://localhost:8000/blog/user
@WebServlet("/teachertable")
public class TeacherTableController extends HttpServlet {
	private final static String TAG = "TeacherTableController : ";
	private static final long serialVersionUID = 1L;

	public TeacherTableController() {
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
			return new TeacherTableInputAction();
		} else if (cmd.equals("inputProc")) {
			return new TeacherTableInputProcAction();
		} else if (cmd.equals("status")) {
			return new TeacherTableStatusAction();
		} 

		return null;
	}

}