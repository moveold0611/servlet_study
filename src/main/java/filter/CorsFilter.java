package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;


@WebFilter("*")
public class CorsFilter extends HttpFilter implements Filter {       

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*"); // 요청서버 필터
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		// 이 위로는 전처리
		chain.doFilter(request, response); // servlet 호출 지점 (전,후처리의 기준점)
		// 이 아래로는 후처리
	}
}
