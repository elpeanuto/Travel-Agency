package edu.elpeanuto.tms.model;

import edu.elpeanuto.tms.model.enums.Gender;
import edu.elpeanuto.tms.model.enums.UserStatus;

/**
 * User model class, contains information about client
 */
public class User extends Entity<User> {
    private Long id;
    private Long userInfoId;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private UserStatus status;
    private String realName;
    private String realSurname;
    private Gender gender;
    private String dateOfBirth;
    private String citizenship;
    private String passportSerial;
    private String passportNumber;
    private String passportValidDate;

    public User() {

    }

    public User(Long userInfoId, String name, String password, String email, String phoneNumber, UserStatus status) {
        this.userInfoId = userInfoId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public User(Long userInfoId, String name, String password, String email, String phoneNumber, UserStatus status,
                String realName, String realSurname, Gender gender, String dateOfBirth, String citizenship, String passportSerial,
                String passportNumber, String passportValidDate) {
        this.userInfoId = userInfoId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.realName = realName;
        this.realSurname = realSurname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.passportValidDate = passportValidDate;
    }

    public User(Long id, Long userInfoId, String name, String password, String email, String phoneNumber, UserStatus status) {
        this.id = id;
        this.userInfoId = userInfoId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public User(Long id, Long userInfoId, String name, String password, String email, String phoneNumber, UserStatus status,
                String realName, String realSurname, Gender gender, String dateOfBirth, String citizenship, String passportSerial,
                String passportNumber, String passportValidDate) {
        this.id = id;
        this.userInfoId = userInfoId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.realName = realName;
        this.realSurname = realSurname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.passportValidDate = passportValidDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealSurname() {
        return realSurname;
    }

    public void setRealSurname(String realSurname) {
        this.realSurname = realSurname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportValidDate() {
        return passportValidDate;
    }

    public void setPassportValidDate(String passportValidDate) {
        this.passportValidDate = passportValidDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userInfoId=" + userInfoId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status='" + status + '\'' +
                ", realName='" + realName + '\'' +
                ", realSurname='" + realSurname + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", passportSerial='" + passportSerial + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportValidDate='" + passportValidDate + '\'' +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }
}
