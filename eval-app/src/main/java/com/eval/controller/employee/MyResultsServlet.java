package com.eval.controller.employee;

import com.eval.dao.EmployeeDAO;
import com.eval.dao.EvaluationDAO;
import com.eval.model.Employee;
import com.eval.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/employee/results")
public class MyResultsServlet extends HttpServlet {
    private final EmployeeDAO empDAO = new EmployeeDAO();
    private final EvaluationDAO evalDAO = new EvaluationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Employee e = empDAO.findByUserId(u.getId());
        if (e != null) req.setAttribute("evaluations", evalDAO.findByEmployeeId(e.getId()));
        req.getRequestDispatcher("/WEB-INF/views/employee/results.jsp").forward(req, resp);
    }
}
