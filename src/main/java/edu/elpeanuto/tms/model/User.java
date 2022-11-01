package edu.elpeanuto.tms.model;

import edu.elpeanuto.tms.servies.exception.EnumParseException;

import java.util.Arrays;

/**
 * User model class, contains information about client
 */
public class User extends Entity<User> {
    public enum STATUS {Banned, Client, Manager, Admin, Leader}
    public enum GENDER {Male, Female}

    private Long id;
    private Long userInfoId;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private String status;
    private String realName;
    private String realSurName;
    private String gender;
    private String dateOfBirth;
    private String citizenship;
    private String passportSerial;
    private String passportNumber;
    private String passportValidDate;

    public User() {

    }

    public User(Long userInfoId, String name, String password, String email, String phoneNumber, String status) throws EnumParseException {
        this.userInfoId = userInfoId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;

        if (isStatusCorrect(status))
            this.status = status;
        else
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");

    }

    public User(Long userInfoId, String name, String password, String email, String phoneNumber, String status,
                String realName, String realSurName, String dateOfBirth, String gender, String citizenship, String passportSerial,
                String passportNumber, String passportValidDate) throws EnumParseException {
        this.userInfoId = userInfoId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.realName = realName;
        this.realSurName = realSurName;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.passportValidDate = passportValidDate;

        if (isStatusCorrect(status))
            this.status = status;
        else
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");

        if (isGenderCorrect(gender))
            this.gender = gender;
        else
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
    }

    public User(Long id, Long userInfoId, String name, String password, String email, String phoneNumber, String status) throws EnumParseException {
        this.id = id;
        this.userInfoId = userInfoId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;

        if (isStatusCorrect(status))
            this.status = status;
        else
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
    }

    public User(Long id, Long userInfoId, String name, String password, String email, String phoneNumber, String status,
                String realName, String realSurName, String gender, String dateOfBirth, String citizenship,
                String passportSerial, String passportNumber, String passportValidDate) throws EnumParseException {
        this.id = id;
        this.userInfoId = userInfoId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.realName = realName;
        this.realSurName = realSurName;
        this.dateOfBirth = dateOfBirth;
        this.citizenship = citizenship;
        this.passportSerial = passportSerial;
        this.passportNumber = passportNumber;
        this.passportValidDate = passportValidDate;

        if (isStatusCorrect(status))
            this.status = status;
        else
            throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");

        if (gender != null)
            if (isGenderCorrect(gender))
                this.gender = gender;
            else
                throw new EnumParseException("Enum value not equal to HTML value, please check your form inout values");
        else
            this.gender = null;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealSurName() {
        return realSurName;
    }

    public void setRealSurName(String realSurName) {
        this.realSurName = realSurName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    private boolean isStatusCorrect(String str) {
        return Arrays.stream(STATUS.values()).anyMatch(v -> v.name().equals(str));
    }

    private boolean isGenderCorrect(String str) {
        return Arrays.stream(GENDER.values()).anyMatch(v -> v.name().equals(str));
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
                ", realSurName='" + realSurName + '\'' +
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
