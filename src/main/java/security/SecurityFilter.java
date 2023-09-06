package security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResponseUtil;


@WebFilter({ "*" })
public class SecurityFilter extends HttpFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String rootPath = "/servlet_study_ldh";
		String uri = req.getRequestURI();
		String[] antMachers = {"/auth"};
		String token = req.getHeader("Authorization");

		
		
		
		// 인증이 필요 없는 경우
		for(String antMacher : antMachers) {
			if(uri.startsWith(rootPath + antMacher)) {
				chain.doFilter(request, response);
				return;
			}
		}
				
		if(!req.getMethod().equalsIgnoreCase("options") && !SecurityContextHolder.isAuthenticated(token)) {
			ResponseUtil.response(resp).of(401).body("인증 실패");
			return;
		}
	
		
		
		chain.doFilter(request, response);
	}
}
