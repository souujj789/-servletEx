package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vo.member;
/**
 * 회원을 수정하는 페이지
 */
@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 목록에서 수정버튼을 클릭했을 때 요청을 처리하는 부분
     * 수정페이지로 이동
     */   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용준비
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
						
		//작업
		//1.데이터베이스 준비
		Connection conn = null;
		PreparedStatement stmt = null;	
		ResultSet rs = null;
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
			//해당번호에 해당하는 자료를 읽어온다.(1개)
			sql = "SELECT mon, mname, email, pwd FROM members where mon=?";//추가 
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, request.getParameter("mon")); //insert.jsp=>name으로 선언한 이름
					
			rs = stmt.executeQuery();
			
			//4. 데이터베이스 조작 후 처리 결과
			//처리결과를 vo변수들을 배열화시켜서 저장
			member members = new member(); //vo로 한명의 자료를 저장
			rs.next();
			//결과물을 vo에 저장(setter를 사용)				
			members.setMon(rs.getInt("mon"));
			members.setMname(rs.getString("mname"));
			members.setEmail(rs.getString("email"));
			members.setPwd(rs.getString("pwd"));
						
			//6. 페이지이동전에 전달할 변수를 저장
			//이름은 선언된 arraylist이름으로 값은 arralylist의 이름
			request.setAttribute("members", members); //전달
								
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {rs.close();} catch(Exception e) {}
			try {stmt.close();} catch(Exception e) {}
			try {conn.close();} catch(Exception e) {}
		}
						
		//페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
		rd.forward(request, response); //rd.include()
	}

	/**
	 * 회원수정 폼에서 수정버튼을 눌렀을 때 처리하는 부분
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용준비
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
								
		//작업
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
			sql = "UPDATE members SET mname=?, email=?, pwd=? where mon=?";//추가 
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, request.getParameter("tname")); //insert.jsp=>name으로 선언한 이름
			stmt.setString(2, request.getParameter("email"));
			stmt.setString(3, request.getParameter("pwd"));
			stmt.setString(4, request.getParameter("mon"));
			
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

}
