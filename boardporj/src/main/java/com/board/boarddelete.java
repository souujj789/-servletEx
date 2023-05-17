package com.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class boarddelete
 */
@WebServlet("/delete")
public class boarddelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boarddelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>게시판</title>");
		out.print("<body>");
		//todo:이곳까지 완성..db작업 진행
		out.print("<h3>게시물 삭제</h3>");
		out.print("<form method='post' action='delete'>");
		out.print("<table border=1>");
		out.print("<tr>");
		out.print("<td width=100>삭제할 번호</td>");
		out.print("<td width=200><input type='number' name='tno'></td>");
		out.print("</tr>");
		out.print("<tr><td colspan='2'>");
		out.print("<input type='submit' value='삭제'>");
		out.print("<input type='reset' value='지우기'>");
		out.print("</td></tr>");
		out.print("</table");
		out.print("</form>");
		out.print("</body>");
		out.print("</html>");
		out.close(); //단위테스트
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
//-------------------------------------------------------		
		Connection conn = null;
		//Statement stmt = null;
		PreparedStatement stmt = null;
		//ResultSet rs = null;
		String sql = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/servlet",
					"sample","1234"
					);
			//SQL문을 삭제에 맞게 수정, 값설정(+,-)
			sql="DELETE FROM board where tno=?";
			stmt = conn.prepareStatement(sql);			
			stmt.setInt(1, Integer.parseInt(request.getParameter("tno")));
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//try {rs.close();}catch(Exception e) {}
			try {stmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
//-------------------------------------------------------
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>게시판</title>");
		out.print("<body>");
		out.print("<h3>게시글을 삭제하였습니다.");
		out.print("</body>");
		out.print("</html>");
		out.close(); //단위테스트
	}

}
