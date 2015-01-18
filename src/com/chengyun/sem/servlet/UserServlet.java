package com.chengyun.sem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chengyun.sem.bll.UserManage;
import com.chengyun.sem.model.Command;
import com.chengyun.sem.util.Logger;

public class UserServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3447793591279899034L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			String strCmd = request.getParameter("command");
			if(strCmd == null)
				return;
			switch(Command.valueOf(strCmd)){
			case Usage:
				getUsage(request, response);
				break;
			case Login:
				login(request, response);
				break;
			case Register:
				register(request, response);
				break;
			case GetVerifyCode:
				generateVerifyCode(request, response);
				break;
			case UpdatePassword:
				updatePassword(request, response);
				break;
			default:
				unsupportCommand(request, response);
				break;
			}
		} catch(Exception e){
			Logger.error(e);
		}
	}
	/**
	 * url:username=&password=
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username == null || password == null){
			return;
		}
		int ret = UserManage.login(username, password) ? 0 : 1;
		PrintWriter writer = response.getWriter();
		writer.write(String.valueOf(ret));
	}
	/**
	 * url:username=&password
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username == null || password == null){
			return;
		}
		int ret = UserManage.addUser(username, password) ? 0 : 1;
		PrintWriter writer = response.getWriter();
		writer.write(String.valueOf(ret));
	}
	/**
	 * url:username=&password=&verifycode=
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifycode");
		if(username == null || password == null || verifyCode == null){
			return;
		}
		int ret = UserManage.updatePassword(username, password, verifyCode) ? 0 : 1;
		PrintWriter writer = response.getWriter();
		writer.write(String.valueOf(ret));
	}
	/**
	 * url:username=
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void generateVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String verifyCode = UserManage.generateVerifyCode(username);
		PrintWriter writer = response.getWriter();
		if(verifyCode != null){
			writer.write(verifyCode);
		} else{
			writer.write("Failed to send verify code! Please connect administrator!");
		}
	}
	
	/**
	 * url:username=
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getUsage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String usage = "<html>"
				+ "<p> This page provides FIVE interfaces:"
				+ "<p>1. http://web_name/sem?command=Usage"
				+ "<p>2. http://web_name/sem?command=Login&username=username&password=password"
				+ "<p>3. http://web_name/sem?command=Register&username=username&password=password"
				+ "<p>4. http://web_name/sem?command=GetVerifyCode&username=username"
				+ "<p>5. http://web_name/sem?command=UpdatePassword&username=username&password=password&verifycode=verifycode"
				+ "</html>";
		response.getWriter().write(usage);
	}

	private void unsupportCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.getWriter().write("Unknown Command");
	}
}
