package se.uppsalamakerspace.iot.repository;

import org.springframework.data.repository.CrudRepository;
import se.uppsalamakerspace.iot.model.VoiceMessage;

import java.util.List;

/**
 * Created by fredl2 on 2016-11-08.
 */
public interface VoiceMessageRepository extends CrudRepository<VoiceMessage, String> {

    List<VoiceMessage> findAll();

}
