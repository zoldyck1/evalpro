package com.eval.controller.auth;

import com.eval.dao.UserDAO;
import com.eval.model.Role;
import com.eval.model.User;
import com.eval.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/common/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User u = (email == null) ? null : userDAO.findByEmail(email.trim().toLowerCase());
        if (u == null || !PasswordUtil.verify(password, u.getSalt(), u.getPasswordHash())) {
            req.setAttribute("error", "Invalid email or password");
            req.getRequestDispatcher("/WEB-INF/views/common/login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("user", u);

        String ctx = req.getContextPath();
        switch (u.getRoleName()) {
            case Role.EMPLOYEE: resp.sendRedirect(ctx + "/employee/dashboard"); break;
            case Role.MANAGER:  resp.sendRedirect(ctx + "/manager/dashboard");  break;
            case Role.RH_ADMIN: resp.sendRedirect(ctx + "/admin/dashboard");    break;
            default: resp.sendRedirect(ctx + "/login");
        }
    }
}
