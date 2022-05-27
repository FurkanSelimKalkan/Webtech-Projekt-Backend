package htw.berlin.webtechprojekt.demo.service;

import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.persistence.VotingRepository;
import htw.berlin.webtechprojekt.demo.web.Voting;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotingService {

    private final VotingRepository votingRepository;

    public VotingService(VotingRepository votingRepository){
        this.votingRepository = votingRepository;
    }

    public List<Voting> findAll(){
        List<VotingEntity> votings = votingRepository.findAll();
        return votings.stream()
                .map(votingEntity -> new Voting(
                        votingEntity.getId(),
                        votingEntity.getTitle(),
                        votingEntity.getImage1(),
                        votingEntity.getImage2(),
                        votingEntity.getVotingsImage1(),
                        votingEntity.getVotingsImage2()
                ))
                .collect(Collectors.toList());
    }

}
