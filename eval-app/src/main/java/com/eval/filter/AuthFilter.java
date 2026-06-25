package com.eval.filter;

import com.eval.model.Role;
import com.eval.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class AuthFilter implements Filter {

    @Override public void init(FilterConfig fc) {}
    @Override public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  request  = (HttpServletRequest)  req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI().substring(request.getContextPath().length());
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        boolean ok =
            (path.startsWith("/employee/") && Role.EMPLOYEE.equals(user.getRoleName())) ||
            (path.startsWith("/manager/")  && Role.MANAGER.equals(user.getRoleName()))  ||
            (path.startsWith("/admin/")    && Role.RH_ADMIN.equals(user.getRoleName()));

        if (!ok) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        chain.doFilter(req, res);
    }
}
