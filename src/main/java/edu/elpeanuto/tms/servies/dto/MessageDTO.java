package edu.elpeanuto.tms.servies.dto;

/**
 * Messages DTO class which carries data between processes
 */
public class MessageDTO {
    private Long messageId;
    private Long adminId;
    private String response;
    private String processingDate;

    public MessageDTO(Long messageId, Long adminId, String response, String processingDate) {
        this.messageId = messageId;
        this.adminId = adminId;
        this.response = response;
        this.processingDate = processingDate;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "messageId=" + messageId +
                ", adminId=" + adminId +
                ", response='" + response + '\'' +
                ", processingDate='" + processingDate + '\'' +
                '}';
    }
}
