package se.uppsalamakerspace.iot.service;

import org.springframework.stereotype.Service;

/**
 * Created by fredl2 on 2016-12-13.
 */

@Service
public class CitizenSoundDataSource implements SoundDataSource {
    private final DataStorageService dataStorageService;

    public CitizenSoundDataSource(DataStorageService dataStorageService) {
        this.dataStorageService = dataStorageService;
    }

    @Override
    public String getQueueName() {
        return "citizen";
    }

    @Override
    public byte[] getAudioForMessage(final String messageId) {
        return dataStorageService.retrieveBytes("voice-" + messageId);
    }
}
