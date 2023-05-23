package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 서버구동시 자동으로 실행되는 클래스
 * 1. 공통으로 사용할 작업 및 준비작업
 * 2. 데이터베이스 연결
 */
public class AppInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 서버가 구동되었을 때 실행하는 메소드
	 */
	public void init(ServletConfig config) throws ServletException {
		// 데이터베이스 연결
		System.out.println("서블릿 준비...");
		super.init(config); //서블릿에 초기 설정
		
		//web.xml에 선언된 context-param 정보를 읽어온다.
		//데이터베이스 드라이버, 데이터베이스주소, 아이디, 비밀번호
		ServletContext sc = this.getServletContext();
		
		//데이터베이스 연결
		try {
			Class.forName(sc.getInitParameter("driver"));

			Connection conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")
					);

			sc.setAttribute("conn", conn); //현재 설정을 web.xml에 저장
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 서버가 종료되었을 때 실행하는 메소드
	 */
	public void destroy() {
		// 데이터베이스 종료
		System.out.println("서블릿 종료....");
		super.destroy(); //프로젝트 종료
		
		//데이터베이스 연결
		Connection conn = (Connection)this.getServletContext().getAttribute("conn");
		
		try {
			//만약에 데이터베이스가 연결되었거나, 종료되지 않았으면
			if(conn!=null && conn.isClosed() == false)
				conn.close(); //데이터베이스를 종료
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
