package htw.berlin.webtechprojekt.demo;

import htw.berlin.webtechprojekt.demo.service.UserService;
import htw.berlin.webtechprojekt.demo.web.api.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("should return found users from user service")
    void should_return_found_user_from_user_service() throws Exception {
        // given
        var users = List.of(
                new User(1,"Michael", "michael@gmail.com", "1234", Collections.emptyList()),
                new User(2,"James", "james@gmail.com", "1234", Collections.emptyList())
        );
        doReturn(users).when(userService).findAll();

        // when
        mockMvc.perform(get("/api/v1/users"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("Michael"))
                .andExpect(jsonPath("$[0].email").value("michael@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("1234"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].username").value("James"))
                .andExpect(jsonPath("$[1].email").value("james@gmail.com"))
                .andExpect(jsonPath("$[1].password").value("1234"));

    }

    @Test
    @DisplayName("should return 404 if user is not found")
    void should_return_404_if_person_is_not_found() throws Exception {
        // given
        doReturn(null).when(userService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/users/123"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a user")
    void should_return_201_http_status_and_location_header_when_creating_a_person() throws Exception {
        // given
        String personToCreateAsJson = "{\"username\": \"Michael\", \"email\":\"michael@gmail.com\", \"password\":\"1234\"}";
        var user = new User(123, null, null, null, null);
        doReturn(user).when(userService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(personToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/users/" + user.getId()))));
//            .andExpect(header().string("Location", Matchers.containsString(Long.toString(person.getId()))));

    }

    @Test
    @DisplayName("should validate create user request")
    void should_validate_create_person_request() throws Exception {
        // given
        String personToCreateAsJson = "{\"username\": \"Michael\", \"email\":\"michaeladdgmail.com\", \"password\":\"1234\"}";

        // when
        mockMvc.perform(
                        post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(personToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }
}
