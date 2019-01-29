package webapp.forTRLogic.bean;

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
