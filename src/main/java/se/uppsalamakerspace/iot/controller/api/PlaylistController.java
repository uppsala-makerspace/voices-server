package se.uppsalamakerspace.iot.controller.api;

import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.uppsalamakerspace.iot.model.PlaylistItem;
import se.uppsalamakerspace.iot.service.PlaylistService;
import se.uppsalamakerspace.iot.service.StationService;

import java.util.List;

/**
 * Created by fredl2 on 13/11/16.
 *
 * Responsible for generating playlists of voice messages for stations.
 */

@RestController
public class PlaylistController {

    private final StationService stationService;
    private final PlaylistService playlistService;

    public PlaylistController(StationService stationService, PlaylistService playlistService) {
        this.stationService = stationService;
        this.playlistService = playlistService;
    }

    @Transactional
    @GetMapping("/api/v1/playlist")
    public List<PlaylistItem> generatePlaylist(Authentication auth, @RequestParam("amount") long amount,
                                               @RequestParam(name="queue",required=false) String queueName) {
        List<PlaylistItem> items = playlistService.getPlaylistForStation(stationService.getStationByAuth(auth).orElseThrow(() -> stationNotFound(auth)), amount, queueName);
        items.forEach(item -> {
            playlistService.incrementMessagePlayback(item.getMessage());
        });
        return items;
    }

    private StationService.StationNotFoundException stationNotFound(Authentication auth) {
        return new StationService.StationNotFoundException("Station med ID " + auth.getName() + " finns inte.");
    }
}
