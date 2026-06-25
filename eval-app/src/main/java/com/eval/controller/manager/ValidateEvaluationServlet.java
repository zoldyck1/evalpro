package com.eval.controller.manager;

import com.eval.dao.EvaluationDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/manager/validate")
public class ValidateEvaluationServlet extends HttpServlet {
    private final EvaluationDAO evalDAO = new EvaluationDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        evalDAO.validate(id);
        resp.sendRedirect(req.getContextPath() + "/manager/history");
    }
}
