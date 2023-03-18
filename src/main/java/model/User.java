package model;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private String nationality;
    private char gender;
    private String phone;
    private int points;
    public User() { }

    public User(String firstName,
                String lastName,
                String email,
                String password,
                LocalDate dateOfBirth,
                String nationality,
                char gender,
                String phone,
                int points) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.gender = gender;
        this.phone = phone;
        this.points = points;
    }
    public User(Long userId,
                String firstName,
                String lastName,
                String email,
                String password,
                LocalDate dateOfBirth,
                String nationality,
                char gender,
                String phone,
                int points) {
        this.id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.gender = gender;
        this.phone = phone;
        this.points = points;
    }
    public User(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.dateOfBirth = user.getDateOfBirth();
        this.nationality = user.getNationality();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.points = user.getPoints();
    }

    public Long getId() { return id; }
    public void setId(Long userId) { this.id = userId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password;  }
    public void setPassword(String password) { this.password = password; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public char getGender() { return gender;  }
    public void setGender(char gender) { this.gender = gender; }
    public String getPhone() { return phone;  }
    public void setPhone(String phone) { this.phone = phone;  }
    public int getPoints() { return points;  }
    public void setPoints(int points) { this.points = points;  }

    @Override
    public String toString() {
        return "User {" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + dateOfBirth +
                ", nationality='" + nationality + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", points=" + points +
                '}';
    }
}
