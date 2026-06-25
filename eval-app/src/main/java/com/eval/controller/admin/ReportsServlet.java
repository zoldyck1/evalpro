package com.eval.controller.admin;

import com.eval.dao.EvaluationDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/reports")
public class ReportsServlet extends HttpServlet {
    private final EvaluationDAO evalDAO = new EvaluationDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("avgByDept", evalDAO.avgScoreByDepartment());
        int[] counts = evalDAO.statusCounts();
        req.setAttribute("draftCount", counts[0]);
        req.setAttribute("validatedCount", counts[1]);
        req.getRequestDispatcher("/WEB-INF/views/admin/reports.jsp").forward(req, resp);
    }
}
