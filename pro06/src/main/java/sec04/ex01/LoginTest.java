package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <form  method = "post" action="login5" encType = "UTF-8">
 */
@WebServlet("/login5")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * request로 자료를 받는 방법은 총 3가지(외부에서 들어오는 값을 처리하는 방법)
	 * request.getParameter(변수명) : 해당 변수의 값을 읽어온다.
	 * request.getValues(변수명) : 해당 변수의 여러개의 값을 읽어온다.
	 * request.getParameterNames() : 변수명을 모를 때 값들을 읽어온다.(키,값)
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 받는 자료 문자셋(request)
		request.setCharacterEncoding("UTF-8");
		
		//2. 받는 자료 저장(사용할 변수만 저장)
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_address = request.getParameter("user_address");
		
		//3. 유효성 검사(내가 원하는 값이거나, 원하는 모양인지를 확인)
		//if 참, 거짓(값 비교), switch 참, 거짓(메뉴 항목)
		//아이디에 값이 없거나, 길이가 0이면 ==>내용없음
		//if(user_id==null || (user_id.length()==0))
		//아이디가 null이 아니면서, 길이가 0이 아니면
		
		//5. 보낸 자료 문자셋(response)
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		//if(user_id!=null && (user_id.length()!=0))
		//아이디에 값이 없거나, 길이가 0이 아니면(!)		
		if(!(user_id==null || (user_id.length()==0))) {
			out.print("<html>");
			out.print("<body>");
			out.print(user_id+"님이 로그인하였습니다.");
			out.print("</body>");
			out.print("</html>");
		}else {
			out.print("<html>");
			out.print("<body>");
			out.print("아이디를 입력하세요");
			out.print("<a href = 'http://localhost:8080/pro06/login5.html'>페이지이동</a>");
			out.print("</body>");
			out.print("</html>");
		}
		
		//4. 작업진행(데이터 베이스)
		
		//6. 보내는 자료 출력

		
	}

}
