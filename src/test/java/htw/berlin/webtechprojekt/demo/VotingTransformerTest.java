package htw.berlin.webtechprojekt.demo;

import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.service.VotingTransformer;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;

public class VotingTransformerTest implements WithAssertions {

    private final VotingTransformer underTest = new VotingTransformer();

    @Test
    @DisplayName("should transform VotingEntity to Voting")
    void votingEntityToVoting () {
        // given
        var votingEntity = Mockito.mock(VotingEntity.class);
        doReturn(111L).when(votingEntity).getId();
        doReturn("Welches?").when(votingEntity).getTitle();
        doReturn("www.bild.com").when(votingEntity).getImage1();
        doReturn("www.bild2.com").when(votingEntity).getImage2();
        doReturn(5).when(votingEntity).getVotingsImage1();
        doReturn(2).when(votingEntity).getVotingsImage2();
        doReturn("Max").when(votingEntity).getOwner();
        doReturn(new ArrayList<>()).when(votingEntity).getVotedUsers();

        // when
        var result = underTest.transformEntity(votingEntity);

        // then
        assertThat(result.getId()).isEqualTo(111);
        assertThat(result.getTitle()).isEqualTo("Welches?");
        assertThat(result.getImage1()).isEqualTo("www.bild.com");
        assertThat(result.getImage2()).isEqualTo("www.bild2.com");
        assertThat(result.getVotingsImage1()).isEqualTo(5);
        assertThat(result.getVotingsImage2()).isEqualTo(2);
        assertThat(result.getOwnerId()).isEqualTo("Max");
        assertThat(result.getVotedUsers()).hasSize(0);

    }
}
