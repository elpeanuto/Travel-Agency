package edu.elpeanuto.tms.model;

/**
 * Message model class, contains information about message
 */
public class Message extends Entity<Message> {
    private Long id;
    private Long messageAnswerId;
    private Long userId;
    private Long adminId;
    private String userEmail;
    private String userName;
    private String category;
    private String message;
    private String answer;
    private String receivedDate;
    private String processingDate;

    public Message(Long id, Long messageAnswerId, Long userId, String userEmail, String userName, String category, String message, String receivedDate) {
        this.id = id;
        this.messageAnswerId = messageAnswerId;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.category = category;
        this.message = message;
        this.receivedDate = receivedDate;
    }

    public Message(Long id, Long messageAnswerId, Long userId, Long adminId, String userEmail, String userName, String category, String message, String answer,
                   String receivedDate, String processingDate) {
        this.id = id;
        this.messageAnswerId = messageAnswerId;
        this.userId = userId;
        this.adminId = adminId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.category = category;
        this.message = message;
        this.answer = answer;
        this.receivedDate = receivedDate;
        this.processingDate = processingDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getMessageAnswerId() {
        return messageAnswerId;
    }

    public void setMessageAnswerId(Long messageAnswerId) {
        this.messageAnswerId = messageAnswerId;
    }

    @Override
    public int compareTo(Message o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userid=" + userId +
                ", adminId=" + adminId +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", category='" + category + '\'' +
                ", message='" + message + '\'' +
                ", answer='" + answer + '\'' +
                ", receivedDate='" + receivedDate + '\'' +
                ", processedDate='" + processingDate + '\'' +
                '}';
    }
}
