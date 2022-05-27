package htw.berlin.webtechprojekt.demo;

import htw.berlin.webtechprojekt.demo.web.api.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VotingRestController {

    private List<Voting> votings;


    public VotingRestController(){
        votings = new ArrayList<>();
        votings.add(new Voting(1l, "Title1", "image1", "image2", 5,20));
    }

    @GetMapping(path = "/api/v1/votings")
    public ResponseEntity<List<Voting>> fetchVotings(){
        return ResponseEntity.ok(votings);
    }

/*    @PostMapping("/votings")
    public ResponseEntity<Void> createVoting(@RequestBody VotingCreateRequest request){
        //....
    } */
}
