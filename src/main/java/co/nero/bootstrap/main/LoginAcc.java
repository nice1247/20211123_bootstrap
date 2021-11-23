package co.nero.bootstrap.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.nero.bootstrap.comm.Command;
import co.nero.bootstrap.member.service.MemberService;
import co.nero.bootstrap.member.service.MemberVO;
import co.nero.bootstrap.member.serviceImpl.MemberServiceImpl;

public class LoginAcc implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 모델을 호출해서 결과를 처리함
		HttpSession session = request.getSession();
		MemberService memberDao = new MemberServiceImpl();
		MemberVO member = new MemberVO();
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("password"));
		member = memberDao.memberSelect(member);
		String viewPage = null;
		if (member != null) {
			session.setAttribute("id", member.getId());
			session.setAttribute("author", member.getAuthor());
			session.setAttribute("name", member.getName());
			viewPage = "main.do";
		} else {
			viewPage= "main/login";
		}

		
		return "main/main";
	}

}
