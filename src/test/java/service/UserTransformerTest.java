package service;

import htw.berlin.webtechprojekt.demo.persistence.UserEntity;
import htw.berlin.webtechprojekt.demo.persistence.VotingEntity;
import htw.berlin.webtechprojekt.demo.service.UserTransformer;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

public class UserTransformerTest implements WithAssertions {

    private final UserTransformer underTest = new UserTransformer();

    @Test
    @DisplayName("should transform VotingEntity to Voting")
    public void should_transform_entity_to_person() {
        // given
        List<VotingEntity> votingEntities = new ArrayList<>();
        var votingEntity = Mockito.mock(VotingEntity.class);
        doReturn(30L).when(votingEntity).getId();
        doReturn("this or that").when(votingEntity).getTitle();
        doReturn("url1").when(votingEntity).getImage1();
        doReturn("url2").when(votingEntity).getImage1();
        doReturn(1).when(votingEntity).getVotingsImage1();
        doReturn(2).when(votingEntity).getVotingsImage2();
        votingEntities.add(votingEntity);

        var userEntity = Mockito.mock(UserEntity.class);
        doReturn(111L).when(userEntity).getId();
        doReturn("user1").when(userEntity).getUsername();
        doReturn("1234").when(userEntity).getPassword();
        doReturn("test@gmail.com").when(userEntity).getEmail();
        doReturn(votingEntities).when(userEntity).getVotings();

        // when
        var result = underTest.transformEntity(userEntity);


        // then
        assertThat(result.getId()).isEqualTo(111);
        assertThat(result.getUsername()).isEqualTo("user1");
        assertThat(result.getPassword()).isEqualTo("1234");
        assertThat(result.getEmail()).isEqualTo("test@gmail.com");
        assertThat(result.getVotingIds()).isEqualTo(List.of(30L));
    }
}
