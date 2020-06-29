package com.bitc.practiceProgress.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitc.practiceProgress.action.Action;
import com.bitc.practiceProgress.action.classTableAction.ClassTableDetailAction;
import com.bitc.practiceProgress.action.classTableAction.ClassTableDetailDeleteProcAction;
import com.bitc.practiceProgress.action.classTableAction.ClassTableDetailUpdateProcAction;
import com.bitc.practiceProgress.action.classTableAction.ClassTableHomeAction;
import com.bitc.practiceProgress.action.classTableAction.ClassTableInputAction;
import com.bitc.practiceProgress.action.classTableAction.ClassTableInputProcAction;

// http://localhost:8000/blog/user
@WebServlet("/classtable")
public class ClassTableController extends HttpServlet {
	private final static String TAG = "ClassTableController : ";
	private static final long serialVersionUID = 1L;

	public ClassTableController() {
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
		if (cmd.equals("home")) {
			// 회원가입 페이지로 이동
			return new ClassTableHomeAction();
		} else if (cmd.equals("input")) {
			// 회원가입 페이지로 이동
			return new ClassTableInputAction();
		} else if (cmd.equals("inputProc")) {
			// 회원가입 페이지로 이동
			return new ClassTableInputProcAction();
		} else if (cmd.equals("detail")) {
			// 회원가입 페이지로 이동
			return new ClassTableDetailAction();
		} else if (cmd.equals("detailUpdateProc")) {
			// 회원가입 페이지로 이동
			return new ClassTableDetailUpdateProcAction();
		} else if (cmd.equals("detailDeleteProc")) {
			// 회원가입 페이지로 이동
			return new ClassTableDetailDeleteProcAction();
		}

		return null;
	}

}