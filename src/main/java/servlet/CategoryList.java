package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet("/category")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] categoryArray = {
		"한식",
		"체험관광",
		"카페",
		"자연명소",
		"양식",
		"문화예술"
	};
	
	private class Feed {
		private String feedName;
		private String categoryName;
		
		public Feed(String feedName, String categoryName) {
			this.feedName = feedName;
			this.categoryName = categoryName;
		}
		
		public String getCategoryName() {
			return categoryName;
		}
		
		public String getFeedInfo() {
			return "feedName:" + feedName + ", categoryName:" + categoryName + "\n"; 
		}
	}
	
	private Feed[] feedArray = {
		new Feed("1번피드", "한식"),
		new Feed("2번피드", "체험관광"),
		new Feed("3번피드", "카페"),
		new Feed("4번피드", "자연명소"),
		new Feed("5번피드", "양식"),
		new Feed("6번피드", "문화예술"),
		new Feed("7번피드", "자연명소"),
		new Feed("8번피드", "양식"),
		new Feed("9번피드", "문화예술"),
		new Feed("10번피드", "체험관광"),
		new Feed("11번피드", "문화예술"),
		new Feed("12번피드", "한식"),
		new Feed("13번피드", "한식"),
	};
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
//		System.out.println(req.getRequestURI());
//		System.out.println(req.getMethod());
		String categoryName = req.getParameter("categoryName");
	
		if(!checkCategory(categoryName)) {
			resp.setCharacterEncoding("UTF-8"); // 한글 인코딩
			resp.setStatus(400); // 반환코드 설정
			resp.getWriter().println("해당 카테고리는 존재하지 않는 카테고리입니다."); // Writter불러와서 print			
			return;
		}

		// 프론트에서 사용하기 간편하도록 json형태로 변환
		Gson gson = new Gson(); 
		resp.setContentType("application/json");
		resp.getWriter().println(gson.toJson(findFeedByCategory(categoryName)).toString());
				
		//내가 한거
//		List<Feed> feeds = findFeedByCategory(categoryName);
//		for(int i = 0; i < feeds.size(); i++) {
//			resp.getWriter().print(feeds.get(i).getFeedInfo());
//		}
				
		// 선생님 예시  ->  (AtomicReference = 람다식으로 외부 변수를 바꾸기 위함(전역주소공유))
//		AtomicReference<String> respData = new AtomicReference<String>("");
//		findFeedByCategory(categoryName).forEach(feed -> {
//			respData.set(respData.get() + feed.getFeedInfo());
//		});
//		resp.getWriter().println(respData.getPlain());	
	}
	
	private List<Feed> findFeedByCategory(String categoryName) {
		List<Feed> feeds = new ArrayList<>();
		
		for(int i = 0; i < feedArray.length; i++) {
			if(feedArray[i].getCategoryName().equals(categoryName)) {
				feeds.add(feedArray[i]);
			}
		}
		return feeds;
	}
	
	private boolean checkCategory(String categoryName) {
		for(int i = 0; i < categoryArray.length; i++) {
			if(categoryArray[i].equals(categoryName)) {
				return true;
			}
		}
		return false;
	}
	
	

}
