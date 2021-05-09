package com.example.teacherspet;

public class MessageSt {

    private String messageSId, messageS, senderId;

    public String getMessageSId() {
        return messageSId;
    }

    public MessageSt() {
    }

    public MessageSt(String messageS, String senderId) {
        this.messageS = messageS;
        this.senderId = senderId;
    }

    public void setMessageSId(String messageSId) {
        this.messageSId = messageSId;
    }

    public String getMessageS() {
        return messageS;
    }

    public void setMessageS(String messageS) {
        this.messageS = messageS;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
