package htw.berlin.webtechprojekt.demo;

import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.persistence.VotingRepository;
import htw.berlin.webtechprojekt.demo.service.VotingService;
import htw.berlin.webtechprojekt.demo.service.VotingTransformer;
import htw.berlin.webtechprojekt.demo.web.api.Voting;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class VotingServiceTest implements WithAssertions {

    @Mock
    private VotingRepository repository;

    @Mock
    private VotingTransformer votingTransformer;

    @InjectMocks
    private VotingService underTest;

    @Test
    @DisplayName("should return true if delete was successful")
    void should_return_true_if_delete_was_successful() {
        // given
        Long givenId = 111L;
        doReturn(true).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteById(givenId);

        // then
        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("should return false if person to delete does not exist")
    void should_return_false_if_person_to_delete_does_not_exist() {
        // given
        Long givenId = 111L;
        doReturn(false).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteById(givenId);

        // then
        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("should return the Voting with the correct ID")
    void returnsVotingWithCorrectId() {
        // given
        List<Voting> votingsList = new ArrayList();
        List<Voting> votingsListResult = new ArrayList();
        List<VotingEntity> votingsEntityList = new ArrayList();

        VotingEntity votingEntity = new VotingEntity("Titel", "www.google.com", "www.google2.com", 0, 0, "test", "egal", Collections.emptyList());
        Voting voting = new  Voting(111l,"Titel", "www.google.com", "www.google2.com", 0, 0, "test", "egal", Collections.emptyList());

        votingsList.add(voting); votingsListResult.add(voting); votingsEntityList.add(votingEntity);

        doReturn(voting).when(votingTransformer).transformEntity(votingsEntityList.get(0));
        doReturn(votingsEntityList).when(repository).findAll();

        // when
        votingsListResult = underTest.findAll();

        // then
        assertThat(votingsListResult).isEqualTo(votingsList);
    }
}
