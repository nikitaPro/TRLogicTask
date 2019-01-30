package webapp.forTRLogic.bean;


public class User extends NewUser {
    protected long id;
    
    public User(long id, String name, String lastName, String email) {
        super(name, lastName, email);
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
