package se.uppsalamakerspace.iot.service;

/**
 * Created by fredl2 on 2016-12-13.
 *
 * An interface defining how to interact with a sound data source,
 * resolved by matching the message queue name against getQueueName()
 * taking a message ID and returning the bytes containing the audio.
 */
public interface SoundDataSource {
    String getQueueName();
    byte[] getAudioForMessage(String messageId);
}
