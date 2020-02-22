package cui.toughtstorm.core.servlet;

import cui.toughtstorm.core.dao.ToughtStormDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteTought")
public class DeleteToughtServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toughtId = req.getParameter("toughtIdForDel");
        new ToughtStormDAOImpl().deleteTought(toughtId);

        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
