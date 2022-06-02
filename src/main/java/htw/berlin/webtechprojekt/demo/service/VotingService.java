package htw.berlin.webtechprojekt.demo.service;

import htw.berlin.webtechprojekt.demo.persistence.UserRepository;
import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.persistence.VotingRepository;
import htw.berlin.webtechprojekt.demo.web.api.Voting;
import htw.berlin.webtechprojekt.demo.web.api.VotingManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotingService {

    private final VotingRepository votingRepository;
    private final UserRepository userRepository;
    private final UserTransformer userTransformer;

    public VotingService(VotingRepository votingRepository, UserRepository userRepository, UserTransformer userTransformer) {
        this.votingRepository = votingRepository;
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    public List<Voting> findAll(){
        List<VotingEntity> votings = votingRepository.findAll();
        return votings.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Voting findById(Long id){
        var votingEntity = votingRepository.findById(id);
        return votingEntity.map(this::transformEntity).orElse(null);
    }

    public Voting create(VotingManipulationRequest request){
        var owner = userRepository.findById(request.getOwnerId()).orElseThrow();
        var votingEntity = new VotingEntity(request.getTitle(), request.getImage1(), request.getImage2(), request.getVotingsImage1(), request.getVotingsImage2(), owner);
       votingEntity =  votingRepository.save(votingEntity);
       return transformEntity(votingEntity);
    }

    public Voting update(Long id, VotingManipulationRequest request) {
        var votingEntityOptional = votingRepository.findById(id);
        if (votingEntityOptional.isEmpty()) {
            return null;
        }

        var votingEntity = votingEntityOptional.get();
        votingEntity.setTitle(request.getTitle());
        votingEntity.setImage1(request.getImage1());
        votingEntity.setImage2(request.getImage2());
        votingEntity.setVotingsImage1(request.getVotingsImage1());
        votingEntity.setVotingsImage2(request.getVotingsImage2());
        votingEntity = votingRepository.save(votingEntity);

        return transformEntity(votingEntity);
    }

    public boolean deleteById(Long id) {
        if (!votingRepository.existsById(id)) {
            return false;
        }

        votingRepository.deleteById(id);
        return true;
    }

    private Voting transformEntity(VotingEntity votingEntity){
        return new Voting(
                votingEntity.getId(),
                votingEntity.getTitle(),
                votingEntity.getImage1(),
                votingEntity.getImage2(),
                votingEntity.getVotingsImage1(),
                votingEntity.getVotingsImage2(),
                userTransformer.transformEntity(votingEntity.getOwner())
        );
    }
}
