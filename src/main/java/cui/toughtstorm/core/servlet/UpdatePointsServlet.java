package cui.toughtstorm.core.servlet;

import cui.toughtstorm.core.dao.ToughtStormConnection;
import cui.toughtstorm.core.dao.ToughtStormDAOImpl;
import cui.toughtstorm.core.dao.view.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/updatePoints")
public class UpdatePointsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String toughtId = req.getParameter("toughtId");
        String operation = req.getParameter("operation");
        System.out.println("ID tought: " + toughtId + " operation: " + operation);
        User user = (User) req.getSession().getAttribute("user");
        System.out.println("User:" + user.getUsername());
        try {
            ToughtStormDAOImpl daoObj = new ToughtStormDAOImpl();
            Connection connection = new ToughtStormConnection().getConnection();
            if(!daoObj.isVoted(connection, user.getId(), toughtId, operation)) {
               daoObj.addVote(connection, user.getId(), toughtId, operation);
                daoObj.updateToughtPoints(connection, toughtId, operation);
                if(user.getId().equals(daoObj.getUserIdFromToughtId(connection, toughtId)))
                    user.setScore(daoObj.getUserScore(connection, user.getId()));
            }

            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);

    }
}
