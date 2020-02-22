package cui.toughtstorm.core.servlet;

import cui.toughtstorm.core.dao.ToughtStormConnection;
import cui.toughtstorm.core.dao.ToughtStormDAOImpl;
import cui.toughtstorm.core.dao.view.Tought;
import cui.toughtstorm.core.dao.view.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/postTought")
public class PostToughtServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String postContent = req.getParameter("post-toughtContent-textarea");
        System.out.println("User: " + user.getUsername() + " Tought: " + postContent);
        try {
            Tought tought = new Tought();
            tought.setUserId(user.getId());
            tought.setContent(postContent);
            Connection connection = new ToughtStormConnection().getConnection();
            new ToughtStormDAOImpl().postTought(connection, tought);
            connection.close();

            req.getRequestDispatcher("/home.jsp").forward(req, resp);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
