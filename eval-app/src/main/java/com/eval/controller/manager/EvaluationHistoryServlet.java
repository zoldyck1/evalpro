package com.eval.controller.manager;

import com.eval.dao.EvaluationDAO;
import com.eval.dao.ManagerDAO;
import com.eval.model.Manager;
import com.eval.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/manager/history")
public class EvaluationHistoryServlet extends HttpServlet {
    private final EvaluationDAO evalDAO = new EvaluationDAO();
    private final ManagerDAO mDAO = new ManagerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Manager m = mDAO.findByUserId(u.getId());
        if (m != null) req.setAttribute("evaluations", evalDAO.findByManagerId(m.getId()));
        req.getRequestDispatcher("/WEB-INF/views/manager/history.jsp").forward(req, resp);
    }
}
