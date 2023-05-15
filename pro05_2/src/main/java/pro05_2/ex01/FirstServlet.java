/*
 * 수동으로 서블릿을 구성하는 연습
 * 1. 서블릿을 상속
 * 2. 필요한 메소드를 추가
 * 3. 각 메소드별로 프로그램을 작성
 * 4. web.xml에 맵핑
 */

package pro05_2.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet{ //상속
	//Servlet 프로그램을 구현(인터페이스 구성 - 메소드명은 정의된 상태로 이용)
	//내용은 사용자가 임의로 작성
	//doGet, doPost 모든 메소드는 클래스에 1번씩만 사용 가능
	
	//서블릿을 초기에 실행할 때(초기값, 1회만 구동)
	@Override
	public void init() throws ServletException {
		System.out.println("init");
	}	
	//외부 요청이 있을 때 처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 메소드 호출");
	}
	//외부 요청이 있을 때 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost 메소드 호출");
	}
	//서블릿이 소멸되기 직전에 처리하는 부분(자원반환)
	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	//서블릿의 생명주기(init~doGet~doPost~destroy => 네가지가 충족된 서블릿)

}
