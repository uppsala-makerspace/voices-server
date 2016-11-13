package se.uppsalamakerspace.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.uppsalamakerspace.iot.model.PlaylistItem;
import se.uppsalamakerspace.iot.model.Station;
import se.uppsalamakerspace.iot.model.VoiceMessage;
import se.uppsalamakerspace.iot.repository.VoiceMessageRepository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by fredl2 on 13/11/16.
 *
 * Service responsible for generating playlists for stations
 */

@Service
public class PlaylistService {

    private final VoiceMessageRepository messageRepo;

    @Autowired
    public PlaylistService(VoiceMessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<PlaylistItem> getPlaylistForStation(Station station, long numberOfMessages) {
        final AtomicInteger queueCounter = new AtomicInteger(0);
        return messageRepo.findAll().stream() //FIXME: naive approach, fetches all messages in db
                .map(this::scoreMessage)
                .sorted(compareMessageByWeight)
                .map(VoiceMessageWeightWrapper::getMessage)
                .limit(numberOfMessages)
                .map(msg -> makePlaylistItem(msg, queueCounter))
                .collect(Collectors.toList());
    }

    private VoiceMessageWeightWrapper scoreMessage(VoiceMessage message) {
        double weight = 1.0;

        long avgPlays = getAverageAmountOfPlays();

        if(message.getNumberPlaybacks() >= avgPlays) {
            weight = weight * 0.5;
        }

        return new VoiceMessageWeightWrapper(message, weight);
    }

    public void incrementMessagePlayback(VoiceMessage message) {
        if(message == null) {
            return;
        }
        if(message.getNumberPlaybacks() == null) {
            message.setNumberPlaybacks(0L);
        }
        message.setNumberPlaybacks(message.getNumberPlaybacks() + 1);
        messageRepo.save(message);
    }

    private long getAverageAmountOfPlays() {
        return (long) Math.ceil(messageRepo.findAll().stream()
                .filter(msg -> msg.getNumberPlaybacks() != null)
                .mapToLong(VoiceMessage::getNumberPlaybacks)
                .average()
                .orElseThrow(() -> new RuntimeException("Could not calculate average number of voice plays")));

    }

    private Comparator<VoiceMessageWeightWrapper> compareMessageByWeight = (msg1, msg2) -> {
        if(msg1.getWeight() > msg2.getWeight()) {
            return 1;
        } else if (msg1.getWeight() < msg2.getWeight()) {
            return -1;
        } else {
            return 0;
        }
    };

    private PlaylistItem makePlaylistItem(final VoiceMessage voiceMessage, AtomicInteger numberEnqueued) {
        return new PlaylistItem(voiceMessage, (numberEnqueued.getAndIncrement() * 10)); //FIXME: Actually calculate message length
    }

    private static class VoiceMessageWeightWrapper {
        private final double weight;
        private final VoiceMessage voiceMessage;


        public VoiceMessageWeightWrapper(VoiceMessage message, double score) {
            this.voiceMessage = message;
            this.weight = score;
        }

        public double getWeight() {
            return weight;
        }

        public VoiceMessage getMessage() {
            return voiceMessage;
        }
    }
}
