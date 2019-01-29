package webapp.forTRLogic.constants;

public class Query {
    /**For PreparedStatment. Parameters: name, last name, phone, email, pass*/
    public static final String INSERT_NEW_USER 
    = "INSERT INTO user_table VALUES (DEFAULT, ?, ?, ?, ?, ?);";
}
