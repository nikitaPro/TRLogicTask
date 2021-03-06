package webapp.forTRLogic.constants;

public class Query {
    /**For PreparedStatment. Parameters: name, last name, phone, email, pass*/
    public static final String INSERT_NEW_USER 
    = "INSERT INTO user_table VALUES (DEFAULT, ?, ?, ?, ?) RETURNING id;";
    
    /**For PreparedStatment. Parameters: user_id, phone*/
    public static final String INSERT_USER_PHONE 
    = "INSERT INTO phones VALUES (?, ?);";
    
    /**For PreparedStatment. Parameters: id*/
    public static final String SELECT_USER_BY_ID 
    = "SELECT name, lastname, email FROM user_table WHERE id = ?;";
    
    /**For PreparedStatment. Parameters: email*/
    public static final String SELECT_USER_BY_EMAIL 
    = "SELECT id, name, lastname FROM user_table WHERE email = ?;";
    
    /**For PreparedStatment. Parameters: id*/
    public static final String SELECT_USER_PASSWORD_BY_ID 
    = "SELECT pass FROM user_table WHERE id = ?;";
    
    /**For PreparedStatment. Parameters: user_id*/
    public static final String SELECT_USERS_PHONES_BY_ID 
    = "SELECT phone FROM phones WHERE user_id = ?;";
}
