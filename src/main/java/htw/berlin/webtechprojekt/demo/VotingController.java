package htw.berlin.webtechprojekt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VotingController {

    @Autowired
    VotingService service;

    @PostMapping("/votings")
    public Voting createVoting(@RequestBody Voting voting){
        return service.save(voting);
    }

    @GetMapping("/votings/{id}")
    public Voting getService(@PathVariable String id){
        Long votingId = Long.parseLong(id);
        return service.get(votingId);
    }
}
