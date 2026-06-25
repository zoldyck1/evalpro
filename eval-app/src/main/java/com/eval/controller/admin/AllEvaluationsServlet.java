package com.eval.controller.admin;

import com.eval.dao.EvaluationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/evaluations")
public class AllEvaluationsServlet extends HttpServlet {
    private final EvaluationDAO evalDAO = new EvaluationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("evaluations", evalDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/views/admin/evaluations.jsp").forward(req, resp);
    }
}
