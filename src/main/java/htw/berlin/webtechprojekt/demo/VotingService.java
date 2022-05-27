package htw.berlin.webtechprojekt.demo;

import htw.berlin.webtechprojekt.demo.Voting;
import htw.berlin.webtechprojekt.demo.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {
    @Autowired
    VotingRepository repo;

    public VotingService(VotingRepository repo) {
        this.repo = repo;
    }

    public Voting save(Voting voting){
        return repo.save(voting);
    }

    public Voting get(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
