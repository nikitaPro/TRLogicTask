package webapp.forTRLogic.bean;

import java.util.ArrayList;
import java.util.List;

public class NewUser {

    protected String name;
    protected String lastName;
    protected List<String> phones;
    protected String email;
     
    public NewUser(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.phones = new ArrayList<>();
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public List<String> getPhones() {
        return phones;
    }
    
    public void addPhone(String phone) {
        phones.add(phone);
    }
    
    public String getEmail() {
        return email;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
    
}
