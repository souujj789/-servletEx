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
 * Servlet implementation class boardupdate
 */
@WebServlet("/update")
public class boardupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boardupdate() {
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
		out.print("<h3>게시물 수정</h3>");
		out.print("<form method='post' action='update'>");
		out.print("<table border=1>");
		out.print("<tr>"); //기본키에 필요한 부분만 추가
		out.print("<td width=100>번호</td>");
		out.print("<td width=200><input type='number' name='tno'></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<td width=100>제목</td>"); //불필요한 부분은 제거
		out.print("<td width=200><input type='text' name='tsubject'></td>");
		out.print("</tr>");
		out.print("<tr>");
		out.print("<td width=100>내용</td>");
		out.print("<td width=200><textarea name='tcontent' cols='50' rows='5'></textarea></td>");	
		out.print("</tr>");
		out.print("<tr><td colspan='2'>");
		out.print("<input type='submit' value='수정'>");
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
			//SQL문을 수정에 맞게 수정, 값설정(+,-)
			sql="UPDATE board SET tsubject=?,tcontent=? where tno=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, request.getParameter("tsubject"));
			stmt.setString(2, request.getParameter("tcontent"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("tno")));
			
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
		out.print("<h3>게시글을 수정하였습니다.");
		out.print("</body>");
		out.print("</html>");
		out.close(); //단위테스트
	}

}
