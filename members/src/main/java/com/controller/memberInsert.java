package com.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.memberDAO;
import com.vo.member;

/**
 * 삽입버튼을 눌렀을 때 doGet으로 입력폼(insert.jsp)이동
 * 입력폼에서 등록버튼을 눌렀을 때 doPost로 데이터베이스를 처리
 */
@WebServlet("/insert")
public class memberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * list.jsp에서 삽입버튼을 눌렀을 때
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//준비
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//이동
		RequestDispatcher rd = request.getRequestDispatcher("insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * insert.jsp에서 등록버튼을 눌렀을 때
	 * 데이터베이스에 관련 자료를 저장
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//준비
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//데이터베이스 작업
		try {
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection) sc.getAttribute("conn");
			
			//값준비(데이터형변환, 값을 저장)
			member temp = new member(); //com.vo.member 선언
			temp.setMname(request.getParameter("tname"));
			temp.setEmail(request.getParameter("email"));
			temp.setPwd(request.getParameter("pwd"));
			
			memberDAO memberdao = new memberDAO();
			memberdao.setConnection(conn);
			
			memberdao.insert(temp); //가공된 temp전달
		} catch(Exception e) {
			
		}
		
		
		//이동
		response.sendRedirect("list");
	}

}
