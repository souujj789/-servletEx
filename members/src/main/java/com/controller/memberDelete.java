package com.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.memberDAO;
/**
 * 지정한 번호를 이용해서 회원을 삭제하고, list.jsp 페이지로 이동
 */
@WebServlet("/delete")
public class memberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 삭제 후 list 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//준비
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//데이터베이스 작업
		try {
			ServletContext sc = this.getServletContext(); //web.xml
			Connection conn = (Connection)sc.getAttribute("conn");
			
			//list.jsp에서 삭제할 번호를 받아와서 저장
			int mon = Integer.parseInt(request.getParameter("mon"));
			
			memberDAO memberdao = new memberDAO(); //사용할 dao 선언
			memberdao.setConnection(conn); //연결
			memberdao.delete(mon); //사용할 메소드를 동작
			
		}catch(Exception e) {
			
		}
		
		//이동
		response.sendRedirect("list"); //관련없는 페이지로 이동
	}

}
