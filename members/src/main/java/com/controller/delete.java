//com.controller
//맵핑 작업을 위한 패키지
//servlet에서는 파일과 servlet이 1:1 매칭
//Spring에서는 기능별 servlet이 1개(게시판-1개, 회원관리-1개)
package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 목록에서 삭제버튼을 클릭했을 때 처리하는 컨트롤
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * 삭제 폼없이 삭제 작업후 list로 이동
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용준비
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
						
		//작업(데이터베이스에 연결해서 members테이블에 레코드를 조회)
		//결과값들을 vo에 저장
		//1.데이터베이스 준비
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		
		try {
			//2. 데이터베이스 연결
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/servlet",
					"sample",
					"1234"
					);//주소:포트/데이터베이스명,아이디,비밀번호
			
			//3. 데이터베이스 작업
			sql = "DELETE FROM members WHERE mon=?";//해당번호를 삭제
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(request.getParameter("mon")));
			stmt.executeUpdate();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {			
			try {stmt.close();} catch(Exception e) {}
			try {conn.close();} catch(Exception e) {}
		}
						
		//페이지 이동
		response.sendRedirect("list"); //작업컨트롤로 이동
		
	}

	/**
	 * 삭제에서는 post를 필요가 없다.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
