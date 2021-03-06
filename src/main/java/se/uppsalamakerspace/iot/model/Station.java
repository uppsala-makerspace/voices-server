package se.uppsalamakerspace.iot.model;

import javax.persistence.*;

/**
 * Created by fredl2 on 2016-11-01.
 */
@Entity
@Table(name = "iot_station")
public class Station {

    @Id
    @Column(name = "station_id")
    private String uuid;

    @Column(name = "station_name")
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "station_owner", nullable = false)
    private User owner;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
