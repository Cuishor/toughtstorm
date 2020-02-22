package cui.toughtstorm.core.dao;

import cui.toughtstorm.core.dao.view.Tought;
import cui.toughtstorm.core.dao.view.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ToughtStormDAO {
    boolean logInUser(Connection conn, String username, String password);
    boolean createUser(Connection conn, User userObj);
    boolean isExistingUser(Connection conn, String username) throws SQLException;
    User getUser(Connection conn, String username) throws SQLException;
    List<Tought> getPosts() throws SQLException, ClassNotFoundException;
    void postTought(Connection conn, Tought t);
    void updateToughtPoints(Connection conn, String toughtId, String operation);
    String getUserScore(Connection conn, String userId);
    String getUserIdFromToughtId(Connection conn, String toughtId);
    boolean isVoted(Connection conn, String userId, String toughtId, String operation);
    void addVote(Connection conn, String userId, String toughtId, String operation);
    void deleteTought(String toughtId);
}
