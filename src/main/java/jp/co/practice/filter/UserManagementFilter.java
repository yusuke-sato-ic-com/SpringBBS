package jp.co.practice.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.practice.entity.User;

@WebFilter(urlPatterns ={"/signup","/userEdit","/userManagement","/signup.jsp","/userEdit.jsp","/userManagement.jsp"}) // どのURLに対してか
public class UserManagementFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)request).getSession();

		/*
		 * doFilterメソッドで渡されてくるものがHttpServletRequestではなくServletRequestのため、
		 * キャストをした上でgetSessionを使ってセッションを取得
		 */
		List<String> messages = new ArrayList<String>();

		User loginUser =  (User) session.getAttribute("loginUser");

		if((loginUser.getDepartmentId()).equals(1) == false) {
			// 未承認の場合
			messages.add("アクセス権限がありません。");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse)response).sendRedirect("./");

		} else {
			// 承認済み
			chain.doFilter(request, response);
		}
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
