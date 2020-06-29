package com.bitc.practiceProgress.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	
	public static void outJson(String msg, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void outText(String msg, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getMessage(String msg, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<h1>" + msg + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void back(String msg, HttpServletResponse response) { // 뒤로가기
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("history.back();");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void href(String msg, String uri, HttpServletResponse response) { // 뒤로가기
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("location.href='"+uri+"';");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void href(String uri, HttpServletResponse response) { // 뒤로가기
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href='"+uri+"';");
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void putScript(String msg, String scriptText, HttpServletResponse response) { // 뒤로가기
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println(scriptText);
			out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
