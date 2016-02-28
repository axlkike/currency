package com.axlkike.currency.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * User of currency
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5, max = 10)
    @Column(name="user_id", unique=true)
    @NotNull
    private String userId;

    @Size(min = 5, max = 100)
    @Column(name="password")
    @NotNull
    private String password;

    private String retypePassword;

    @Size(min = 5, max = 100)
    @Column(name="username")
    @NotNull
    private String userName;

    @Size(min = 5, max = 100)
    @Column(name="email")
    @NotNull
    private String email;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name="register_date")
    private DateTime registerDate;

    @Size(min = 1, max = 10)
    @Column(name="role")
    private String role;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(DateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    /**
     * empty constructor
     */
    public User() {   }

    /** Constructor */
    public User(String userId, String password, String userName,
                String email, DateTime registerDate, String role) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.registerDate = registerDate;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + this.userId +
                ", password='" + this.password + "'" +
                ", userName='" + this.userName + "'" +
                ", email='" + this.email + "'" +
                ", registerDate='" + this.registerDate + "'" +
                ", role='" + this.role + "'" +
                "}";
    }
}
