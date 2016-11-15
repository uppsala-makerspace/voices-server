package se.uppsalamakerspace.iot.service;

import se.uppsalamakerspace.iot.model.Station;
import se.uppsalamakerspace.iot.model.VoiceMessage;
import se.uppsalamakerspace.iot.model.Vote;
import se.uppsalamakerspace.iot.model.VoteMessage;
import se.uppsalamakerspace.iot.repository.VoiceMessageRepository;
import se.uppsalamakerspace.iot.repository.VoteRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by fredl2 on 2016-11-15.
 */
public class VoteService {

    private final VoteRepository voteRepo;
    private final VoiceMessageRepository messageRepo;

    public VoteService(VoteRepository voteRepo, VoiceMessageRepository messageRepo) {
        this.voteRepo = voteRepo;
        this.messageRepo = messageRepo;
    }

    public void handleVoteMessages(Station station, List<VoteMessage> messages) {
        voteRepo.save(messages.stream()
                .map(this::convertVoteMessageToVote)
                .map(vote -> addStation(vote, station))
                .collect(Collectors.toList()));
    }

    private Vote convertVoteMessageToVote(VoteMessage voteMessage) {
        Vote vote = new Vote();
        vote.setUuid(UUID.randomUUID().toString());
        vote.setVoiceMessage(messageRepo.findOne(voteMessage.getMessageId()));
        vote.setIsUpvote(voteMessage.getIsUpvote());
        return vote;
    }

    private Vote addStation(Vote vote, Station station) {
        vote.setStation(station);
        return vote;
    }
}
