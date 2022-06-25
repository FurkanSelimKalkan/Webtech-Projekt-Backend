package htw.berlin.webtechprojekt.demo;

import htw.berlin.webtechprojekt.demo.persistence.VotingRepository;
import htw.berlin.webtechprojekt.demo.service.VotingService;
import htw.berlin.webtechprojekt.demo.web.api.Voting;
import htw.berlin.webtechprojekt.demo.web.api.VotingCountManipulationRequest;
import htw.berlin.webtechprojekt.demo.web.api.VotingManipulationRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VotingRestController.class)
public class VotingRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotingService votingService;

    @Mock
    private VotingRepository repository;

    @Test
    @DisplayName("should return found votings from voting service")
    void should_return_found_votings_from_voting_service() throws Exception {
        // given
        var votings = List.of(
                new Voting(1L, "Welches?", "www.google.com", "www.google2.com", 5, 2, "qwer1234", "Max", Collections.emptyList()),
                new Voting(2L, "This?", "www.google.com", "www.google2.com", 3, 0, "abcd1234", "Chris", Collections.emptyList() )
        );
        doReturn(votings).when(votingService).findAll();

        // when
        mockMvc.perform(get("/api/v1/votings"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Welches?"))
                .andExpect(jsonPath("$[0].image1").value("www.google.com"))
                .andExpect(jsonPath("$[0].image2").value("www.google2.com"))
                .andExpect(jsonPath("$[0].votingsImage1").value(5))
                .andExpect(jsonPath("$[0].votingsImage2").value(2))
                .andExpect(jsonPath("$[0].ownerId").value("qwer1234"))
                .andExpect(jsonPath("$[0].userName").value("Max"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("This?"))
                .andExpect(jsonPath("$[1].image1").value("www.google.com"))
                .andExpect(jsonPath("$[1].image2").value("www.google2.com"))
                .andExpect(jsonPath("$[1].votingsImage1").value(3))
                .andExpect(jsonPath("$[1].votingsImage2").value(0))
                .andExpect(jsonPath("$[1].ownerId").value("abcd1234"))
                .andExpect(jsonPath("$[1].userName").value("Chris"));
    }

    @Test
    @DisplayName("should return 404 if voting is not found")
    void should_return_404_if_voting_is_not_found() throws Exception {
        // given
        doReturn(null).when(votingService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/votings/123"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a voting")
    void should_return_201_http_status_and_location_header_when_creating_a_voting() throws Exception {
        // given
        String votingToCreateAsJson = "{\"title\": \"Titel\", \"image1\":\"google.com\", \"image2\":\"google2.com\", \"votingsImage1\": 3,\"votingsImage2\": \"0\", \"ownerId\":\"test\", \"userName\":\"egal\"}";
        var voting = new Voting(123L, null, null, null, 0, 0, null, null, null);
        doReturn(voting).when(votingService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/votings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(votingToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/votings/" + voting.getId()))));

    }

    @Test
    @DisplayName("should validate create voting request")
    void should_validate_create_voting_request() throws Exception {
        // given
        String votingToCreateAsJson = "{\"title\": \"\", \"image1\":\"\", \"image2\":\"\", \"votingsImage1\": ,\"votingsImage2\": \"\", \"ownerId\":\"\", \"userName\":\"\"}";

        // when
        mockMvc.perform(
                        post("/api/v1/votings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(votingToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }




    @Test
    @DisplayName("should return 404 http status and Location header when upvoting a voting which doesnt exist")
    void should_return_200_http_status_and_location_header_when_upvoting_a_voting_which_doesnt_exist() throws Exception {
        // given
        long id = 123l;
        VotingCountManipulationRequest votingCountManipulationRequest = new VotingCountManipulationRequest( 0, 0, "test");
        String votingToUpdateAsJson = "{\"id\": 123,\"title\": \"Titel\", \"image1\":\"google.com\", \"image2\":\"google2.com\", \"votingsImage1\": 0, \"votingsImage2\": 0, \"ownerId\":\"test\", \"userName\":\"egal\", \"votedUsers\":[]}";
   //     String votingToUpdateAsJson = "{\"votingsImage1\": 0,\"votingsImage2\": 0,\"votingUser\":\"test\"}";
        var voting = new Voting(id, "Titel","google.com", "google2.com", 0, 0, "test", "egal", List.of("test"));
        doReturn(voting).when(votingService).addUser(123l, votingCountManipulationRequest);

        // when
        mockMvc.perform(
                        put("/api/v1/votings/" + 123l )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(votingToUpdateAsJson)
                )
                // then
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("Deletes the Voting if the Voting exists")
    void deleteVotingIfExists() throws Exception {
        // given
        Long givenId = 123L;
        doReturn(true).when(repository).existsById(givenId);

        VotingCountManipulationRequest votingCountManipulationRequest = new VotingCountManipulationRequest( 0, 0, "test");
        String votingToDeleteAsJson = "{\"id\": 123,\"title\": \"Titel\", \"image1\":\"google.com\", \"image2\":\"google2.com\", \"votingsImage1\": 0, \"votingsImage2\": 0, \"ownerId\":\"test\", \"userName\":\"egal\", \"votedUsers\":[]}";
        doReturn(true).when(votingService).deleteById(123l);

        // when
        mockMvc.perform(
                        delete("/api/v1/votings/" + givenId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(votingToDeleteAsJson)
                )
                // then
                .andExpect(status().isOk());


    }

    @Test
    @DisplayName("Does not delete the Voting if it doesnt exist")
    void dontDeleteVotingBecauseItDoesntExist() throws Exception {
        // given
        Long givenId = 123L;
        Long doesNotExistId = 12L;
        doReturn(true).when(repository).existsById(givenId);

        VotingCountManipulationRequest votingCountManipulationRequest = new VotingCountManipulationRequest( 0, 0, "test");
        String votingToDeleteAsJson = "{\"id\": 123,\"title\": \"Titel\", \"image1\":\"google.com\", \"image2\":\"google2.com\", \"votingsImage1\": 0, \"votingsImage2\": 0, \"ownerId\":\"test\", \"userName\":\"egal\", \"votedUsers\":[]}";
        doReturn(true).when(votingService).deleteById(123l);

        // when
        mockMvc.perform(
                        delete("/api/v1/votings/" + doesNotExistId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(votingToDeleteAsJson)
                )
                // then
                .andExpect(status().isNotFound());


    }


    @Test
    @DisplayName("should return one voting with the correct ID from voting service")
    void returnVotingWithMatchingId() throws Exception {
        // given

        Voting voting = new Voting(2L, "Titel", "www.google.com", "www.google2.com", 0, 0, "test", "egal", Collections.emptyList());
        doReturn(voting).when(votingService).findById(2L);

        // when

        mockMvc.perform(get("/api/v1/votings/" + 2L))
                // then
                .andExpect(content().json("{\"id\": 2,\"title\": \"Titel\", \"image1\":\"www.google.com\", \"image2\":\"www.google2.com\", \"votingsImage1\": 0, \"votingsImage2\": 0, \"ownerId\":\"test\", \"userName\":\"egal\", \"votedUsers\":[]}"));
    }
}
