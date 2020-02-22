package cui.toughtstorm.core.servlet;

import cui.toughtstorm.core.dao.ToughtStormConnection;
import cui.toughtstorm.core.dao.ToughtStormDAOImpl;
import cui.toughtstorm.core.dao.view.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("login-username-input");
        String password = req.getParameter("login-password-input");
        System.out.println(username);
        System.out.println(password);
        try {
            Connection connection = new ToughtStormConnection().getConnection();
            ToughtStormDAOImpl daoObj = new ToughtStormDAOImpl();
            boolean feedback = daoObj.logInUser(connection, username, password);

            if(feedback) {
                User user = daoObj.getUser(connection, username);
                session.setAttribute("user", user);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/home.jsp").forward(req, resp);

            } else {
                System.out.println("User does not exist.");
                req.setAttribute("errorMessage", "Failed to log in.");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);

            }
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
