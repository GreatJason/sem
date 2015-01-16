package com.chengyun.sem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chengyun.sem.bll.UserManage;

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
		String strCmd = request.getParameter("command");
		if(strCmd == null)
			return;
		UserCommand command = UserCommand.newInstance(Integer.parseInt(strCmd));
		switch(command){
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
			break;
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
		boolean ret = UserManage.getInstance().login(username, password);
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
		boolean ret = UserManage.getInstance().addUser(username, password);
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
		if(username == null || password == null){
			return;
		}
		boolean ret = UserManage.getInstance().updatePassword(username, password, verifyCode);
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
		String verifyCode = UserManage.getInstance().generateVerifyCode(username);
		PrintWriter writer = response.getWriter();
		writer.write(verifyCode);
	}

}
