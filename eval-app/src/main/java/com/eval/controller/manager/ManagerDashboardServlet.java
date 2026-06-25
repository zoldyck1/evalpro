package com.eval.controller.manager;

import com.eval.dao.EmployeeDAO;
import com.eval.dao.EvaluationDAO;
import com.eval.dao.ManagerDAO;
import com.eval.model.Manager;
import com.eval.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/manager/dashboard")
public class ManagerDashboardServlet extends HttpServlet {
    private final ManagerDAO mDAO = new ManagerDAO();
    private final EmployeeDAO eDAO = new EmployeeDAO();
    private final EvaluationDAO evalDAO = new EvaluationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Manager m = mDAO.findByUserId(u.getId());
        req.setAttribute("manager", m);
        if (m != null) {
            req.setAttribute("team", eDAO.findByManagerId(m.getId()));
            req.setAttribute("recentEvals", evalDAO.findByManagerId(m.getId()));
        }
        req.getRequestDispatcher("/WEB-INF/views/manager/dashboard.jsp").forward(req, resp);
    }
}
