package webapp.forTRLogic.bean;

/**
 * Class with two fields: <code>success</code>(true/false) 
 * for mark status like success/error and <code>message</code> 
 * for success or error info.
 * */
public class Status {
    
    protected String message;
    protected boolean success;
    public Status(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public boolean isSuccess() {
        return success;
    }
    
}
