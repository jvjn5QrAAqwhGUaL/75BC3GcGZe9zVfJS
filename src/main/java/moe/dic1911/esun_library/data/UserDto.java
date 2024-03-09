package moe.dic1911.esun_library.data;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashMap;

@Entity
@Table(name = "USERS")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uid;
    private String username;
    private String password;
    private String phoneNum;
    private String name;
    private String salt;


    @Transient
    private String matchingPassword;

    @Transient
    private HashMap<String, String> sessionStorage;

    private Timestamp registeredAt;
    private Timestamp lastLoginAt;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Timestamp getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Timestamp lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public HashMap<String, String> getSessionStorage() {
        if (sessionStorage == null) sessionStorage = new HashMap<>();
        return sessionStorage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name);
        sb.append(", Phone: ").append(phoneNum);
        return sb.toString();
    }
}
