package cui.toughtstorm.core.dao;

import cui.toughtstorm.core.dao.query.Query;
import cui.toughtstorm.core.dao.view.Tought;
import cui.toughtstorm.core.dao.view.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToughtStormDAOImpl implements ToughtStormDAO {
    private Connection connection;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public boolean logInUser(Connection conn, String username, String password) {
        System.out.println("Try to log the user.");
        try {
            connection = conn;

            preparedStatement = connection.prepareStatement(Query.AUTH_USER.getQuery());
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, DigestUtils.sha256Hex(password));
            System.out.println(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();


            System.out.println("User logged in!");
            return true;

        } catch(SQLException ex) {
            System.out.println("Sql error!");
            ex.printStackTrace();

        } catch(Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                preparedStatement.close();
                resultSet.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    @Override
    public boolean createUser(Connection conn, User userObj) {
        try {
            connection = conn;
                preparedStatement = connection.prepareStatement(Query.CREATE_USER.getQuery());
                preparedStatement.setString(1, userObj.getUsername());
                preparedStatement.setString(2, DigestUtils.sha256Hex(userObj.getPassword()));
                preparedStatement.setString(3, userObj.getShortBio());
                preparedStatement.setString(4, userObj.getGender());
                preparedStatement.setString(5, userObj.getDob());

                preparedStatement.executeUpdate();
                System.out.println("User created!");
                return true;

        } catch(SQLException ex) {
            System.out.println("Sql error!");
            ex.printStackTrace();

        } catch(Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                if(preparedStatement != null)
                    preparedStatement.close();
                if(resultSet != null)
                    resultSet.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean isExistingUser(Connection conn, String username) throws SQLException {
        preparedStatement = conn.prepareStatement(Query.CHECK_USER.getQuery());
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    @Override
    public User getUser(Connection conn, String username) throws SQLException {
        preparedStatement = conn.prepareStatement(Query.GET_USER_DATA.getQuery());
        preparedStatement.setString(1, username);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            User user = new User();
            user.setId(String.valueOf(resultSet.getInt("user_id")));
            user.setUsername(resultSet.getString("username"));
            user.setShortBio(resultSet.getString("shortBio"));
            user.setGender(resultSet.getString("gender"));
            user.setDob(resultSet.getString("dob"));
            user.setScore(String.valueOf(resultSet.getInt("score")));

            return user;
        } else {
            System.out.println("Couldn't retrieve user.");
            return null;
        }
    }

    @Override
    public List<Tought> getPosts() {
        try {
            connection = new ToughtStormConnection().getConnection();
            preparedStatement = connection.prepareStatement(Query.GET_POSTS.getQuery());
            resultSet = preparedStatement.executeQuery();
            List<Tought> toughtList = new ArrayList<>();
            while(resultSet.next()) {
                Tought tought = new Tought();
                tought.setId(String.valueOf(resultSet.getInt("tought_id")));
                tought.setContent(resultSet.getString("tought_content"));
                tought.setUsername(resultSet.getString("username"));
                tought.setPoints(String.valueOf(resultSet.getInt("tought_points")));
                tought.setUserId(String.valueOf(resultSet.getInt("userId")));
                toughtList.add(tought);
            }

            return toughtList;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
                resultSet.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void postTought(Connection conn, Tought t) {
        try {
            connection = conn;
            preparedStatement = connection.prepareStatement(Query.ADD_POST.getQuery());
            preparedStatement.setInt(1, Integer.parseInt(t.getUserId()));
            preparedStatement.setString(2, t.getContent());
            preparedStatement.executeUpdate();
            System.out.println("Tought added!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void updateToughtPoints(Connection conn, String toughtId, String operation) {
        try {
            connection = conn;
            if(operation.equals("+")) {
                preparedStatement = connection.prepareStatement(Query.UPDATE_POST_SCORE_ADD.getQuery());
            } else if(operation.equals("-")) {
                preparedStatement = connection.prepareStatement(Query.UPDATE_POST_SCORE_SUB.getQuery());
            } else {
                preparedStatement = connection.prepareStatement(Query.EMPTY_POST_SCORE_UPDATE.getQuery());
            }
            preparedStatement.setInt(1, Integer.parseInt(toughtId));
            preparedStatement.executeUpdate();
            System.out.println("Post points updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getUserScore(Connection conn, String userId) {
        connection = conn;
        try {
            preparedStatement = connection.prepareStatement(Query.GET_USER_SCORE.getQuery());
            preparedStatement.setInt(1, Integer.parseInt(userId));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
               return String.valueOf(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getUserIdFromToughtId(Connection conn, String toughtId) {
        connection = conn;
        try {
            preparedStatement = connection.prepareStatement(Query.GET_USRID_WT_TGTID.getQuery());
            preparedStatement.setInt(1, Integer.parseInt(toughtId));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println("Retrieved user_id.");
                return String.valueOf(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean isVoted(Connection conn, String userId, String toughtId, String operation) {
        try {
            connection = conn;
            preparedStatement = connection.prepareStatement(Query.CHECK_IF_ALREADY_VOTED.getQuery());
            preparedStatement.setInt(1, Integer.parseInt(userId));
            preparedStatement.setInt(2, Integer.parseInt(toughtId));
            preparedStatement.setString(3, operation);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addVote(Connection conn, String userId, String toughtId, String operation) {
        try {
            connection = conn;
            preparedStatement = connection.prepareStatement(Query.ADD_VOTE.getQuery());
            preparedStatement.setInt(1, Integer.parseInt(userId));
            preparedStatement.setInt(2, Integer.parseInt(toughtId));
            preparedStatement.setString(3, operation);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTought(String toughtId) {
        try {
            connection = new ToughtStormConnection().getConnection();
            preparedStatement = connection.prepareStatement(Query.DELETE_TOUGHT.getQuery());
            preparedStatement.setInt(1, Integer.parseInt(toughtId));
            preparedStatement.executeUpdate();
            System.out.println("Post deleted (" + toughtId + ")");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
