package model;

public class User {

    private String name, faculty, email;
    private final String userName, password;

    private char gender;

    // 0. Homo
    // 1. Hetero
    private int sexualOrientation;

    private boolean status;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }

    public User(String userName, String password, String name, String faculty, String email, char gender,
            int sexualOrientation) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.faculty = faculty;
        this.email = email;
        this.gender = gender;
        this.sexualOrientation = sexualOrientation;
        status = true;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
       return (gender == 'm') ? "Masculino" : "Femenino";
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setStatusAccount(boolean b) {
        status = b;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSexualOrientation() {
        return sexualOrientation == 1 ? "Heterosexual" : "Homosexual";
    }

    public void setSexualOrientation(int sexualOrientation) {
        this.sexualOrientation = sexualOrientation;
    }

}
