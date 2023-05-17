package sec03.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login5Servlet
 */
@WebServlet("/login4") //맵핑은 action = "login4"
public class Login5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login5Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * method = get
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// submit 버튼을 눌렀을 때
		//스크립트에 전달
		request.setCharacterEncoding("UTF-8"); //전달값 받기
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		String address = request.getParameter("user_address");	
		
		//전달값 확인(톰캣에 출력)
		System.out.println("아이디 : "+id);
		System.out.println("비밀번호 : "+pw);
		System.out.println("주소 : "+address);
		
		response.setContentType("text/html;UTF-8"); //결과 보내기
		PrintWriter out = response.getWriter();
		
		String data = "<html>";
		data += "<body>";
		data += "아이디 :" +id;
		data += "<br>";
		data += "비밀번호 :" +pw;
		data += "<br>";
		data += "주소 :" +address;
		data += "</body>";
		data += "</html>";
		
		out.print(data);
		
	}

	/**
	 * method = post
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 스크립트에서 전달
		request.setCharacterEncoding("UTF-8"); //전달값 받기
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		String address = request.getParameter("user_address");
		
		//전달값 확인(톰캣에 출력)
		System.out.println("아이디 : "+id);
		System.out.println("비밀번호 : "+pw);
		
		response.setContentType("text/html;charset=UTF-8"); //결과 보내기
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<body>");
		out.print("아이디 : "+id);
		out.print("<br>");
		out.print("비밀번호 : "+pw);
		out.print("<br>");
		out.print("주소 : "+address);
		out.print("</body>");
		out.print("</html>");
		out.close();
	}

}
