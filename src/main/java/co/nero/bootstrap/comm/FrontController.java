package co.nero.bootstrap.comm;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.bootstrap.main.Buttons;
import co.nero.bootstrap.main.Login;
import co.nero.bootstrap.main.LoginAcc;
import co.nero.bootstrap.main.Logout;
import co.nero.bootstrap.main.MainCommand;
import co.nero.bootstrap.notice.command.noticeList;

/**
 * 메인 컨트롤러
 */

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainCommand()); // 시작페이지
		map.put("/noticeList.do", new noticeList()); // 공지사항 목록
		map.put("/buttons.do", new Buttons()); // buttons.html 호출
		map.put("/login.do", new Login());
		map.put("/loginAcc.do", new LoginAcc()); 
		map.put("/logout.do", new Logout());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());

		Command command = map.get(page);
		String viewPage = command.run(request, response);

		if (!viewPage.endsWith(".do")) { // do / tiles / ajax
			if (viewPage.startsWith("ajax:")) {
				// ajax처리 루틴
				return;
			}
			if (viewPage.endsWith(".jsp")) {
				viewPage = "WEB-INF/views/" + viewPage;
			} else {
				viewPage = viewPage + ".tiles"; // tiles layout 사용을 위해
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
