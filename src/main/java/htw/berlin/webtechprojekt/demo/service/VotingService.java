package htw.berlin.webtechprojekt.demo.service;

import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.persistence.VotingRepository;
import htw.berlin.webtechprojekt.demo.web.api.Voting;
import htw.berlin.webtechprojekt.demo.web.api.VotingCountManipulationRequest;
import htw.berlin.webtechprojekt.demo.web.api.VotingManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotingService {

    private final VotingRepository votingRepository;



    public VotingService(VotingRepository votingRepository) {
        this.votingRepository = votingRepository;

    }


    public List<Voting> findAll() {
        List<VotingEntity> votings = votingRepository.findAll();
        return votings.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Voting findById(Long id) {
        var votingEntity = votingRepository.findById(id);
        return votingEntity.map(this::transformEntity).orElse(null);
    }

    public Voting create(VotingManipulationRequest request) {
        var votingEntity = new VotingEntity(request.getTitle(), request.getImage1(), request.getImage2(), request.getVotingsImage1(), request.getVotingsImage2(), request.getOwnerId(), request.getUserName(), request.getVotedUsers());
        votingEntity = votingRepository.save(votingEntity);
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

    public Voting addUser(Long id, VotingCountManipulationRequest request) {
        System.out.println("User ID: " +request.getVotingUser());
        System.out.println("Votings1: " +request.getVotingsImage1());
        System.out.println("Votings2: " +request.getVotingsImage2());
        boolean containsChecker = false;

        var votingEntityOptional = votingRepository.findById(id);
        if (votingEntityOptional.isEmpty()) {
            return null;
        }

        var votingEntity = votingEntityOptional.get();
        List<String> actualVotes = votingEntity.getVotedUsers();


        for (String i : actualVotes) {
            if (i.equals(request.getVotingUser())) {
                containsChecker = true;
                break;
            } else {
                containsChecker = false;
            }
        }

        if (containsChecker == true) {
            votingEntity = votingRepository.save(votingEntity);
            return transformEntity(votingEntity);
        } else {
            actualVotes.add(request.getVotingUser());
            votingEntity.setVotedUsers(actualVotes);
            votingEntity = votingRepository.save(votingEntity);
            return transformEntity(votingEntity);
        }
    }

    public boolean deleteById(Long id) {
        if (!votingRepository.existsById(id)) {
            return false;
        }

        votingRepository.deleteById(id);
        return true;
    }

    private Voting transformEntity(VotingEntity votingEntity) {
        return new Voting(
                votingEntity.getId(),
                votingEntity.getTitle(),
                votingEntity.getImage1(),
                votingEntity.getImage2(),
                votingEntity.getVotingsImage1(),
                votingEntity.getVotingsImage2(),
                votingEntity.getOwner(),
                votingEntity.getUserName(),
                votingEntity.getVotedUsers()

        );
    }
}
