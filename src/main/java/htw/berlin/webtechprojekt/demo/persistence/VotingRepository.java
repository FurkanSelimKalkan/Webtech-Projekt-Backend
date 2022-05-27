package htw.berlin.webtechprojekt.demo.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingRepository extends JpaRepository<VotingEntity, Long> {

    List<VotingEntity> findAllByTitle(String title);
}
