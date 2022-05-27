package htw.berlin.webtechprojekt.demo;

import htw.berlin.webtechprojekt.demo.service.VotingService;
import htw.berlin.webtechprojekt.demo.web.Voting;
import htw.berlin.webtechprojekt.demo.web.api.VotingCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VotingRestController {

    private final VotingService votingService;

    public VotingRestController(VotingService votingService) {
        this.votingService = votingService;
    }

    @GetMapping(path = "/api/v1/votings")
    public ResponseEntity<List<Voting>> fetchVotings(){
        return ResponseEntity.ok(votingService.findAll());
    }

    @PostMapping(path = "/api/v1/votings")
    public ResponseEntity<Void> createVoting(@RequestBody VotingCreateRequest request){
        //....
    }
}
