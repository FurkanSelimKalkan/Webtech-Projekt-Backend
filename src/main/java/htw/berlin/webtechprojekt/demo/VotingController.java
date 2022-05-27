package htw.berlin.webtechprojekt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VotingController {

    @Autowired
    VotingService service;


    @GetMapping("/votings/{id}")
    public Voting getService(@PathVariable String id){
        Long votingId = Long.parseLong(id);
        return service.get(votingId);
    }

    @PostMapping("/votings")
    public ResponseEntity<Void> createVoting(@RequestBody VotingCreateRequest request){
        //....
    }
}
