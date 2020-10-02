package models;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String gender;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String gender) {
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername().toLowerCase(), user.getUsername().toLowerCase()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return getUsername() + ";" + getPassword() + ";" + getGender() + "\n";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
