package htw.berlin.webtechprojekt.demo.service;

import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.persistence.VotingRepository;
import htw.berlin.webtechprojekt.demo.web.api.Voting;
import htw.berlin.webtechprojekt.demo.web.api.VotingCreateRequest;
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
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Voting create(VotingCreateRequest request){
        var votingEntity = new VotingEntity(request.getTitle(), request.getImage1(), request.getImage2(), request.getVotingsImage1(), request.getVotingsImage2());
       votingEntity =  votingRepository.save(votingEntity);
       return transformEntity(votingEntity);
    }

    private Voting transformEntity(VotingEntity votingEntity){
        return new Voting(
                votingEntity.getId(),
                votingEntity.getTitle(),
                votingEntity.getImage1(),
                votingEntity.getImage2(),
                votingEntity.getVotingsImage1(),
                votingEntity.getVotingsImage2()
        );
    }
}
