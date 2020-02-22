package cui.toughtstorm.core.dao.query;

public enum Query {
    CREATE_USER("INSERT INTO user (username, password, short_bio, gender, dob) " +
            "VALUES (?, ?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d'))"),
    CHECK_USER("SELECT 1 FROM user " +
            "WHERE username = ?"),
    AUTH_USER("SELECT 1 FROM user " +
            "WHERE username = ? AND password = ?"),
    GET_USER_DATA("SELECT user_id, username, short_bio as shortBio, dob, gender, score FROM user " +
            "WHERE username = ?"),
    GET_USER_SCORE("SELECT score FROM user " +
            "WHERE user_id = ?"),
    GET_USRID_WT_TGTID("SELECT user_id FROM tought WHERE tought_id = ?"),
    ADD_POST("INSERT INTO tought (user_id, tought_content) " +
                     "VALUES (?, ?)"),
    DELETE_POST("DELETE FROM tought WHERE tought_id = ?"),
    GET_POSTS("SELECT tought_id, username, tought_content, tought_points, t.user_id as userId FROM tought t INNER JOIN user u ON t.user_id = u.user_id"),
    UPDATE_POST_SCORE_ADD("UPDATE tought SET tought_points = tought_points + 1 WHERE tought_id = ?"),
    UPDATE_POST_SCORE_SUB("UPDATE tought SET tought_points = tought_points - 1 WHERE tought_id = ?"),
    CHECK_IF_ALREADY_VOTED("SELECT 1 FROM post_votes WHERE user_id = ? AND tought_id = ? AND vote_type = ?"),
    ADD_VOTE("INSERT INTO post_votes (user_id, tought_id, vote_type) VALUES (?, ?, ?)"),
    EMPTY_POST_SCORE_UPDATE("UPDATE tought SET tought_points = tought_points WHERE tought_id = ?"),
    DELETE_TOUGHT("DELETE FROM tought WHERE tought_id = ?");
    private String query;

    Query(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
