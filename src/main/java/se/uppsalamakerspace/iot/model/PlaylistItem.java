package se.uppsalamakerspace.iot.model;

import java.io.Serializable;

/**
 * Created by fredl2 on 13/11/16.
 *
 * Playlist item. Specifies a message and an offset in seconds.
 * To be put in a List<PlaylistItem>, which may be serialized to JSON
 */

public class PlaylistItem implements Serializable {
    private final VoiceMessage message;
    private final long offset;

    public PlaylistItem(final VoiceMessage message, final long offset) {
        this.message = message;
        this.offset = offset;
    }

    public VoiceMessage getMessage() {
        return message;
    }

    public long getOffset() {
        return offset;
    }
}
