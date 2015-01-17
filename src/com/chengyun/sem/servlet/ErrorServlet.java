package com.chengyun.sem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chengyun.sem.bll.ErrorManage.*;

public class ErrorServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6027966854505358961L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String cmd = request.getParameter("command");
		if(cmd != null && cmd.equals("GetAllErrorInfos")){
			String errors = getAllErrorInfos();
			response.getWriter().write(errors);
		} else{
			response.getWriter().write("Unknown command!");
		}
	}
}
