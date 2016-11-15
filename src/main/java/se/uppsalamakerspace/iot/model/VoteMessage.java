package se.uppsalamakerspace.iot.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by fredl2 on 2016-11-15.
 */

public class VoteMessage {

    private String messageId;
    private Boolean isUpvote;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Boolean getIsUpvote() {
        return isUpvote;
    }

    public void setIsUpvote(Boolean upvote) {
        isUpvote = upvote;
    }
}
