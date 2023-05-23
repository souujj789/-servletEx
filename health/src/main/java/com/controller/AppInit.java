package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * 초기 환경 설정
 */

public class AppInit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet이 시작할 때 동작하는 메소드
	 */
	public void init(ServletConfig config) throws ServletException {
		// 데이터 베이스 연결
		
		super.init(config);//초기화
		
		ServletContext sc = this.getServletContext();//web.xml 파일을 읽어온다.
		
		try {
			//데이터베이스 드라이버
			Class.forName(sc.getInitParameter("driver"));
			//web.xml에서 driver에 저장된 내용을 적용
			//데이터베이스 연결
			Connection conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")
					);
			sc.setAttribute("conn", conn);//데이터베이스 연결정보를 context에 저장
		}catch (Exception e) {
				e.printStackTrace();
		}
		System.out.println("데이터 베이스 연결");
	}

	/**
	 * Servlet이 종료될 때 동작하는 메소드
	 */
	public void destroy() {
		// 데이터 베이스 종료
		super.destroy();//서블릿 종료
		//데이터베이스 연결(성공하면 성공코드, 실패하면 값이 없음)
		Connection conn = (Connection)this.getServletContext().getAttribute("conn");
		//데이터베이스 열려있거나, 닫혀있지 않으면
		try {
			if(conn!=null && conn.isClosed()==false) {
				conn.close();//데이터 베이스 종료
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("데이터 베이스 정상 종료");
	}

}
