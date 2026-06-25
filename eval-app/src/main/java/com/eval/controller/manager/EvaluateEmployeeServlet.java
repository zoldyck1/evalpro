package com.eval.controller.manager;

import com.eval.dao.*;
import com.eval.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/manager/evaluate")
public class EvaluateEmployeeServlet extends HttpServlet {
    private final EvaluationDAO evalDAO = new EvaluationDAO();
    private final EmployeeDAO empDAO   = new EmployeeDAO();
    private final ManagerDAO  mDAO     = new ManagerDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String empIdStr = req.getParameter("employeeId");
        if (empIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/manager/dashboard");
            return;
        }
        int employeeId = Integer.parseInt(empIdStr);

        List<Evaluation> evals = evalDAO.findByEmployeeId(employeeId);
        req.setAttribute("employee",   empDAO.findById(employeeId));
        req.setAttribute("evaluation", evals.isEmpty() ? null : evals.get(0));
        req.getRequestDispatcher("/WEB-INF/views/manager/evaluate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String empIdStr = req.getParameter("employeeId");
        if (empIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/manager/dashboard");
            return;
        }
        User u = (User) req.getSession().getAttribute("user");
        Manager m = mDAO.findByUserId(u.getId());

        int employeeId = Integer.parseInt(empIdStr);
        String scoreStr = req.getParameter("score");
        Integer score = (scoreStr == null || scoreStr.isEmpty()) ? null : Integer.parseInt(scoreStr);
        String comments = req.getParameter("comments");

        Evaluation e = new Evaluation();
        e.setEmployeeId(employeeId);
        e.setManagerId(m.getId());
        e.setScore(score);
        e.setComments(comments);
        e.setStatus(Evaluation.DRAFT);
        evalDAO.create(e);
        resp.sendRedirect(req.getContextPath() + "/manager/dashboard");
    }
}
