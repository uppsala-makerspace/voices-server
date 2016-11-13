package se.uppsalamakerspace.iot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fredl2 on 2016-11-08.
 */

@Entity
@Table(name = "iot_voice_message")
public class VoiceMessage {

    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp")
    private Date timestamp;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @Column(name = "number_playbacks")
    private Long numberPlaybacks;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Long getNumberPlaybacks() {
        return numberPlaybacks;
    }

    public void setNumberPlaybacks(Long numberPlaybacks) {
        this.numberPlaybacks = numberPlaybacks;
    }
}
