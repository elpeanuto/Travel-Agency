package edu.elpeanuto.tms.servies.dto;

/**
 * User DTO class which carries data between processes in session
 */
public class UserDTO {
    private Long id;
    private Long userInfoId;
    private String name;
    private String email;
    private String status;

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public UserDTO() {

    }

    public UserDTO(Long id, Long userInfoId, String name, String email, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userInfoId=" + userInfoId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
