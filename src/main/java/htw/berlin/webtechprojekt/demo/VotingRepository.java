package htw.berlin.webtechprojekt.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends CrudRepository<Voting, Long> {
}
