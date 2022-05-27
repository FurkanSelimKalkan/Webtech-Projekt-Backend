package htw.berlin.webtechprojekt.demo;

import htw.berlin.webtechprojekt.demo.service.VotingService;
import htw.berlin.webtechprojekt.demo.web.api.Voting;
import htw.berlin.webtechprojekt.demo.web.api.VotingCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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

    @GetMapping(path = "/api/v1/votings/{id}")
    public ResponseEntity<Voting> fetchVotingById(@PathVariable Long id){
        var voting = votingService.findById(id);
        return voting != null? ResponseEntity.ok(voting) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/votings")
    public ResponseEntity<Void> createVoting(@RequestBody VotingCreateRequest request) throws URISyntaxException {
        var voting = votingService.create(request);
        URI uri = new URI("/api/v1/votings/" + voting.getId());
        return ResponseEntity.created(uri).build();
    }
}
