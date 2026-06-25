package com.eval.controller.employee;

import com.eval.dao.EmployeeDAO;
import com.eval.dao.EvaluationDAO;
import com.eval.model.Employee;
import com.eval.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/employee/dashboard")
public class EmployeeDashboardServlet extends HttpServlet {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final EvaluationDAO evaluationDAO = new EvaluationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Employee e = employeeDAO.findByUserId(u.getId());
        req.setAttribute("employee", e);
        if (e != null) req.setAttribute("evaluations", evaluationDAO.findByEmployeeId(e.getId()));
        req.getRequestDispatcher("/WEB-INF/views/employee/dashboard.jsp").forward(req, resp);
    }
}
