package com.eval.controller.admin;

import com.eval.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private final EmployeeDAO eDAO = new EmployeeDAO();
    private final ManagerDAO mDAO = new ManagerDAO();
    private final EvaluationDAO evalDAO = new EvaluationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("nbEmployees", eDAO.findAll().size());
        req.setAttribute("nbManagers", mDAO.findAll().size());
        req.setAttribute("nbEvaluations", evalDAO.findAll().size());
        req.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(req, resp);
    }
}
