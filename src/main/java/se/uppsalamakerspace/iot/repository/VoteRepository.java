package se.uppsalamakerspace.iot.repository;

import org.springframework.data.repository.CrudRepository;
import se.uppsalamakerspace.iot.model.Vote;

/**
 * Created by fredl2 on 2016-11-15.
 */
public interface VoteRepository extends CrudRepository<Vote, String> {
}
