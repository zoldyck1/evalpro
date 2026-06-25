package com.eval.controller.manager;

import com.eval.dao.EmployeeDAO;
import com.eval.dao.ManagerDAO;
import com.eval.model.Manager;
import com.eval.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/manager/team")
public class TeamServlet extends HttpServlet {
    private final ManagerDAO mDAO = new ManagerDAO();
    private final EmployeeDAO eDAO = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Manager m = mDAO.findByUserId(u.getId());
        if (m != null) req.setAttribute("team", eDAO.findByManagerId(m.getId()));
        req.getRequestDispatcher("/WEB-INF/views/manager/team.jsp").forward(req, resp);
    }
}
