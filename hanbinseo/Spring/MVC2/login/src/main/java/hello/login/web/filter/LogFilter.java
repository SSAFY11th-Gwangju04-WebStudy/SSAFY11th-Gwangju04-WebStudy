package hello.login.web.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("LogFilter init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		log.info("LogFilter doFilter");

		HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
		String requestURI = httpRequest.getRequestURI();
		String uuid = UUID.randomUUID().toString();

		try {
			log.info("REQUEST [{}][{}]", uuid, requestURI);
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (Exception e) {
			throw e;
		} finally {
			log.info("REQUEST [{}][{}]", uuid, requestURI);
		}
	}

	@Override
	public void destroy() {
		log.info("LogFilter destroy");
	}
}
