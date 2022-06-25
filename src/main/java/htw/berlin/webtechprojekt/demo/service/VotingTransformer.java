package htw.berlin.webtechprojekt.demo.service;

import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.web.api.Voting;
import org.springframework.stereotype.Service;

@Service
public class VotingTransformer {

    public Voting transformEntity(VotingEntity votingEntity) {
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
