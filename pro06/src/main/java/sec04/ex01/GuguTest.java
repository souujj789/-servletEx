package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GuguTest
 */
@WebServlet("/guguTest")
public class GuguTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.문자셋 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//2. 데이터값을 저장
		//문자열->숫자변환 Integer.parseInt(문자열)
		String dan = request.getParameter("dan");
		int idan = Integer.parseInt(dan);//문자열을 정수로 변환해서 저장
		//표를 선두께 1로 생성, 내용은 가운데 정렬
		//게시판, 공지사항, 자료실(표로 구성된 페이지)는 for문으로 변수이름으로 값을 변경하여 사용
		out.print("<table border=1>");
		out.print("<tr>");//1줄
		//colspan=칸합치기, rowspan=줄합치기
		out.print("<td colspan=2 align=center>"+dan+"단 출력</td>");
		out.print("</tr>");
		
		for(int i=1; i<10; i++) {//1~9까지 9번 반복
			out.print("<tr>");//2줄
			out.print("<td width = 200 align=center>"+idan+" * "+i+"=</td>");//1~9까지 9번 반복
			out.print("<td width = 200 align=center>"+(idan*i)+"</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		
	}


}
