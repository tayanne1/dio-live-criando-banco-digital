package model;

public class User {

    private static int counter = 1;

    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) {
        this.id = counter;
        this.name = name;
        this.email = email;
        this.password = password;
        counter += 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "\nId: " + getId() +
               "\nName: " + getName() +
               "\nEmail: " + getEmail() +
               "\nPassword: " + getPassword();
    }
}
