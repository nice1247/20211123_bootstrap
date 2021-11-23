package co.nero.bootstrap.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.bootstrap.comm.Command;
import co.nero.bootstrap.notice.service.NoticeService;
import co.nero.bootstrap.notice.serviceImpl.NoticeServiceImpl;

public class noticeList implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 처리
		NoticeService noticeDao = new NoticeServiceImpl();
		request.setAttribute("notices", noticeDao.noticeSelectList());
		return "notice/noticeList"; 
	}

}
