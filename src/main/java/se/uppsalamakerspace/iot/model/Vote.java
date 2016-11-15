package se.uppsalamakerspace.iot.model;

import javax.persistence.*;

/**
 * Created by fredl2 on 2016-11-15.
 */
@Entity
@Table(name = "iot_vote")
public class Vote {

    @Id
    @Column(name="uuid")
    private String uuid;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="message")
    private VoiceMessage voiceMessage;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="station")
    private Station station;

    @Column(name="is_upvote")
    private Boolean isUpvote;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public VoiceMessage getVoiceMessage() {
        return voiceMessage;
    }

    public void setVoiceMessage(VoiceMessage voiceMessage) {
        this.voiceMessage = voiceMessage;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Boolean getIsUpvote() {
        return isUpvote;
    }

    public void setIsUpvote(Boolean upvote) {
        isUpvote = upvote;
    }
}
