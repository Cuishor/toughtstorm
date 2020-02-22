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
import java.sql.SQLOutput;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("register-username-input");
        String password = req.getParameter("register-password-input");
        String dob = req.getParameter("register-dob-input");
        String gender = req.getParameter("register-gender-select");
        String shortBio = req.getParameter("register-shortBio-textarea");
        try {
            ToughtStormDAOImpl daoObj = new ToughtStormDAOImpl();
            Connection connection = new ToughtStormConnection().getConnection();
            if(!daoObj.isExistingUser(connection, username)) {
                User userObj = new User();
                    userObj.setUsername(username);
                    userObj.setPassword(password);
                    userObj.setDob(dob);
                    userObj.setGender(gender);
                    userObj.setShortBio(shortBio);
               boolean feedback = daoObj.createUser(connection, userObj);
               userObj = null;

               if(feedback) {
                   User retrievedUser = daoObj.getUser(connection, username);
                   if(retrievedUser != null) {
                       session.setAttribute("user", retrievedUser);
                       req.setAttribute("user", retrievedUser);
                       req.getRequestDispatcher("/home.jsp").forward(req, resp);
                   }
               }
            } else {
                req.setAttribute("errorMessage", "User already exists.");
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
