package edu.elpeanuto.tms.servies.dto;

import edu.elpeanuto.tms.model.enums.UserStatus;

/**
 * User DTO class which carries data between processes in session
 */
public class UserDTO {
    private Long id;
    private Long userInfoId;
    private String name;
    private String email;
    private UserStatus status;

    public UserDTO() {

    }

    public UserDTO(Long id, Long userInfoId, String name, String email, UserStatus status) {
        this.id = id;
        this.userInfoId = userInfoId;
        this.name = name;
        this.email = email;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
