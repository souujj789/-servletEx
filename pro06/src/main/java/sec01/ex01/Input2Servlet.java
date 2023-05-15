package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Input2Servlet
 */
@WebServlet("/input2")
public class Input2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Input2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 변수명을 모르는 상태에서 값을 처리하고자 할 때 
		request.setCharacterEncoding("UTF-8");//문자셋
		//열거형으로 값을 저장(컬렉션)
		Enumeration enu = request.getParameterNames();//값을 연속적으로 저장
		//컬렉션은 자료의 끝을 모르기 때문에 while()문 사용
		//정상 값..값이 없으면 -1을 출력해서 while문 종료됨
		while(enu.hasMoreElements()) {//값을 1개씩 읽어온다. (없을 때 까지 반복적으로 읽어온다.)
			String name = (String)enu.nextElement();//키 값 변수이름 읽기
			String[] values = request.getParameterValues(name);//키에 해당하는 값을 읽어온다.
			System.out.println("name = "+name+", values"+values);
			
			for(String value:values) {
				System.out.println("name = "+name+", values"+value);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
