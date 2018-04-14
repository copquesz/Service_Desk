package br.usjt.arqsw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @author Lucas Copque - 816112862
 *
 */
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	/**
	 * Método que realiza o papel do filter em Spring MVC
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();

		if (uri.endsWith("fazer_login") || uri.endsWith("login") || uri.contains("css") || uri.contains("js")
				|| uri.contains("img")) {
			return true;
		}

		if (request.getSession().getAttribute("usuario") != null) {
			return true;
		}
		response.sendRedirect("login");
		return false;
	}
}
