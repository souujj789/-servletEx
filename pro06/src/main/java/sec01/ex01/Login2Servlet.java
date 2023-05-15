package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * login2.html에서 요청시 처리되는 서블릿
 */
@WebServlet("/login2")
public class Login2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 클라이언트에서 받은 값을 저장
		request.setCharacterEncoding("UTF-8");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//2. 서버에서 처리할 작업..(데이터베이스 처리)
		//클라이언트에 전달하기 위한 Html문장을 작성
		String data = "<html>";
		/* data +="<head>";
		data +="<meta charset='UTF-8'>";
		data +="</head>";*/
		data += "<body>";
		data += "아이디 : " + user_id;
		data += "<br>";
		data += "비밀번호 : " + user_pw;
		data += "</body>";
		data += "</html>";
		
		//3. 클라이언트에 전달
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");//head영역 생략할때
		PrintWriter out = response.getWriter();//클라이언트에 쓰기
		out.print(data);//문자열을 클라이언트(브라우저)에 출력
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
