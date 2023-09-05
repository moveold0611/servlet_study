package servlet;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import security.Authentication;
import security.SecurityContextHolder;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/auth/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Object> signinUser 
		= JsonParseUtil.toMap(req.getInputStream());
		Map<String, String> respData = new HashMap<>();
		
		System.out.println(signinUser);
		for(User user : UserData.userList) {
			if(Objects.equals(user.getUsername(), signinUser.get("username")) 
					&& Objects.equals(user.getPassword(), signinUser.get("password"))) {
				String token = UUID.randomUUID().toString();
				SecurityContextHolder.addAuth(new Authentication(user, token));
				respData.put("token", token);
				break;
			}
		}
		ResponseUtil.response(resp).of(200).body(JsonParseUtil.toJson(respData));
	}

}
