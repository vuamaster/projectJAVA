package model;

public class Account {
    private int id;
    private String user;
    private String password;

    public Account() {
    }

    public Account(int id, String user, String password) {
        this.id = id;
        this.user = user;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account[" +
                "id=" + id +
                ", user='" + user + '\'' +
                ']';
    }
}
