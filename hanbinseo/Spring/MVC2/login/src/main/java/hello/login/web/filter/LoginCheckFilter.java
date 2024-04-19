package hello.login.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.PatternMatchUtils;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter {

	private static final String[] whiteList = {"/", "/members/add", "/login", "/logout", "/css/*"};

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
		String requestURI = httpRequest.getRequestURI();

		HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;

		try {
			log.info("인증 체크 필터 시작 {}", requestURI);

			if (isLoginCheckpath(requestURI)) {
				log.info("인증 체크 로직 실행 {}", requestURI);
				HttpSession session = httpRequest.getSession(false);
				if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
					log.info("미인증 사용자 요청 {}", requestURI);
					// 로그인으로 redirect
					httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
					return;
				}
			}
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (Exception e) {
			throw e;
		}finally {
			log.info("인증 체크 필터 종료 {}", requestURI);
		}
	}

	/**
	 * 화이트 리스트의 경우 인증 체크 X
	 */
	private boolean isLoginCheckpath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
	}
}
