package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import utils.JsonParseUtil;
import utils.ResponseUtil;

/*
 * 회원가입 -> 데이터의 추가 (C)
 * DB에 insert
 * 
 * POST 메소드의 특징
 * - 데이터가 주소창에 표시되지 않는다.
 * - BODY에 데이터를 담아서 서버로 전송한다. 
 * - 전송 데이터의 용량 제한이 없다.
 */

@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, Object> userMap = JsonParseUtil.toMap(request.getInputStream());
		System.out.println(userMap);
		
		List<User> userList = UserData.userList;
		User user = User.builder()
				.userId(userList.size() + 1)
				.username((String)userMap.get("username"))
				.password((String)userMap.get("password"))
				.name((String)userMap.get("name"))
				.email((String)userMap.get("email"))
				.build();
		userList.add(user);
		System.out.println("[[[[[[[" + userList + "]]]]]]]");
		
//		System.out.println(userMap.get("username"));
//		System.out.println(userMap.get("password"));
//		System.out.println(userMap.get("name"));
//		System.out.println(userMap.get("email"));
//		System.out.println("회원가입");
		ResponseUtil.response(response).of(201).body("회원가입 완료");
		// 201 - 생성 성공 코드

	}
}


//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	response.setCharacterEncoding("UTF-8");
//	PrintWriter writer = response.getWriter();
//	System.out.println(request.getClass()); // request의 get클래스 = RequestFacade
//	
//	InputStream inputStream = request.getInputStream();
//	BufferedReader brfferedReader =
//			new BufferedReader(new InputStreamReader(inputStream));
//	StringBuilder reqBody = new StringBuilder("");
//
//	while(true) {
//		String data = brfferedReader.readLine();
//		if(data == null) {
//			break;
//		}
//		reqBody.append(data);
//	}
//	
//	
//}
