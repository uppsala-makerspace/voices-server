package se.uppsalamakerspace.iot.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.uppsalamakerspace.iot.model.Vote;
import se.uppsalamakerspace.iot.model.VoteMessage;
import se.uppsalamakerspace.iot.repository.VoiceMessageRepository;
import se.uppsalamakerspace.iot.repository.VoteRepository;
import se.uppsalamakerspace.iot.service.StationService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by fredl2 on 2016-11-15.
 *
 * Handles incoming votes on voice messages.
 */
@RestController
public class VoteController {

    private final VoteRepository voteRepo;
    private final VoiceMessageRepository messageRepo;
    private final StationService stationService;

    @Autowired
    public VoteController(VoteRepository voteRepo, VoiceMessageRepository messageRepo, StationService stationService) {
        this.voteRepo = voteRepo;
        this.messageRepo = messageRepo;
        this.stationService = stationService;
    }

    @PostMapping("/api/v1/vote")
    public void receiveVotes(Authentication auth, @RequestBody List<VoteMessage> messages) {

    }

    private Vote convertVoteMessageToVote(VoteMessage voteMessage) {
        Vote vote = new Vote();
        vote.setUuid(UUID.randomUUID().toString());
        vote.setVoiceMessage(messageRepo.findOne(voteMessage.getMessageId()));
        vote.setIsUpvote(voteMessage.getIsUpvote());
        return vote;
    }
}
