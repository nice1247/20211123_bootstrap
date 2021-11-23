package co.nero.bootstrap.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.nero.bootstrap.comm.Command;

public class Login implements Command {

	@Override
	public String run(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "main/login";
	}

}
