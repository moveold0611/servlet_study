package servlet;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import utils.ResponseUtil;

@WebServlet("/auth/signup/duplicated/username")
public class DuplicatedUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");		
		boolean responseData = false; 
		for(User user : UserData.userList) {
			if(Objects.equals(user.getUsername(), username)) {
				responseData = true;
				ResponseUtil.response(response).of(200).body(true);
				return;
			}
		}
		ResponseUtil.response(response).of(200).body(false);
	}

}
