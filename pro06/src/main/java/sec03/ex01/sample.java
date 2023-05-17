package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login3")//외부와 연결을 위해 가장 중요한 역할
public class sample extends HttpServlet {//클래스명은 중요하지 않다.
	private static final long serialVersionUID = 1L;

    public sample() { //생성자
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * method = "get" 선언되었을때
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//html파일의 <meta charset="UTF-8">과 동일해야함
		System.out.println("Get 전송");
		doHandle(request, response); //doHandel메소드에 받아온 request, response 넣어 처리
		
	}

	/**
	 * method = "post" 선언되었을때
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//html파일의 <meta charset="UTF-8">과 동일해야함
		
		System.out.println("Post 전송");
		doHandle(request, response);
	}
	//doGet과 doPost에서 공통적으로 작업을 할 때
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		System.out.println("아이디 : "+user_id);
		System.out.println("비밀번호 : "+user_pw);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter xx = response.getWriter();
		
		xx.print("<html>");
		xx.print("<title>결과</title>");
		xx.print("아이디 : "+user_id);
		xx.print("<br>");
		xx.print("비밀번호 : "+user_pw);
		xx.print("</html>");
	}

}
