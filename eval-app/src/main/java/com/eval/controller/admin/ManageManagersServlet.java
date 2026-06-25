package com.eval.controller.admin;

import com.eval.dao.*;
import com.eval.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/managers")
public class ManageManagersServlet extends HttpServlet {
    private final ManagerDAO mDAO = new ManagerDAO();
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("new".equals(action) || "edit".equals(action)) {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("manager", mDAO.findById(id));
            }
            req.getRequestDispatcher("/WEB-INF/views/admin/manager-form.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("managers", mDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/views/admin/managers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            mDAO.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/admin/managers");
            return;
        }

        String idStr = req.getParameter("id");
        Manager m = (idStr != null && !idStr.isEmpty()) ? mDAO.findById(Integer.parseInt(idStr)) : new Manager();
        m.setFullName(req.getParameter("fullName"));
        m.setDepartment(req.getParameter("department"));

        if (idStr == null || idStr.isEmpty()) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            int roleId = userDAO.findRoleIdByName(Role.MANAGER);
            int userId = userDAO.create(email.trim().toLowerCase(), password, roleId);
            m.setUserId(userId);
            mDAO.create(m);
        } else {
            mDAO.update(m);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/managers");
    }
}
