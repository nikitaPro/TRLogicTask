package webapp.forTRLogic.bean;

public class NewUser {

    protected String name;
    protected String lastName;
    protected String phone;
    protected String email;
     
    public NewUser(String name, String lastName, String phone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getEmail() {
        return email;
    }
    
}
