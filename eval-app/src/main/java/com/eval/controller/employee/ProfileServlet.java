package com.eval.controller.employee;

import com.eval.dao.EmployeeDAO;
import com.eval.model.Employee;
import com.eval.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/employee/profile")
public class ProfileServlet extends HttpServlet {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Employee e = employeeDAO.findByUserId(u.getId());
        req.setAttribute("employee", e);
        req.getRequestDispatcher("/WEB-INF/views/employee/profile.jsp").forward(req, resp);
    }
}
