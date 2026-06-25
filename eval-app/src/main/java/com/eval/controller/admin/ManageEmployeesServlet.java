package com.eval.controller.admin;

import com.eval.dao.*;
import com.eval.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;


@WebServlet("/admin/employees")
public class ManageEmployeesServlet extends HttpServlet {
    private final EmployeeDAO empDAO = new EmployeeDAO();
    private final ManagerDAO mDAO = new ManagerDAO();
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("new".equals(action) || "edit".equals(action)) {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("employee", empDAO.findById(id));
            }
            req.setAttribute("managers", mDAO.findAll());
            req.getRequestDispatcher("/WEB-INF/views/admin/employee-form.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("employees", empDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/views/admin/employees.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            empDAO.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/admin/employees");
            return;
        }
        String idStr = req.getParameter("id");
        Employee e = (idStr != null && !idStr.isEmpty()) ? empDAO.findById(Integer.parseInt(idStr)) : new Employee();
        e.setFullName(req.getParameter("fullName"));
        e.setPosition(req.getParameter("position"));
        e.setDepartment(req.getParameter("department"));
        String hire = req.getParameter("hireDate");
        e.setHireDate((hire == null || hire.isEmpty()) ? null : Date.valueOf(hire));
        String mid = req.getParameter("managerId");
        e.setManagerId((mid == null || mid.isEmpty()) ? null : Integer.parseInt(mid));

        if (idStr == null || idStr.isEmpty()) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int roleId = userDAO.findRoleIdByName(Role.EMPLOYEE);
            int userId = userDAO.create(email.trim().toLowerCase(), password, roleId);
            e.setUserId(userId);
            empDAO.create(e);
        } else {
            empDAO.update(e);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/employees");
    }
}
