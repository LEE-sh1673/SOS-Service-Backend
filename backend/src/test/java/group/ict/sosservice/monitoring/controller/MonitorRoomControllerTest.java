package group.ict.sosservice.monitoring.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import group.ict.sosservice.common.annotations.AcceptanceTest;
import group.ict.sosservice.common.annotations.WithMockTestUser;
import group.ict.sosservice.common.exception.ErrorType;
import group.ict.sosservice.monitoring.controller.dto.RoomViewRequest;
import group.ict.sosservice.monitoring.model.MonitorRoom;
import group.ict.sosservice.monitoring.model.MonitorRoomRepository;
import group.ict.sosservice.user.exception.InvalidMemberException;
import group.ict.sosservice.user.model.Email;
import group.ict.sosservice.user.model.User;
import group.ict.sosservice.user.model.UserRepository;

@AutoConfigureMockMvc
@AcceptanceTest
class MonitorRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MonitorRoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockTestUser
    @DisplayName("보호 대상자의 모니터링 ID를 조회할 수 있다.")
    void givenChildRegisterRequest_thenResponseOK() throws Exception {
        final User user = userRepository.findByEmail(Email.of("test-user@gmail.com"))
                .orElseThrow(() -> new InvalidMemberException(ErrorType.NOT_FOUND_MEMBER));

        roomRepository.save(MonitorRoom.builder()
            .user(user)
            .build()
        );

        final RoomViewRequest request = RoomViewRequest.builder()
            .email(user.getEmail().getValue())
            .build();

        final ResultActions result = mockMvc.perform(
            post("/api/v1/rooms")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success", is(true)))
            .andExpect(jsonPath("$.response").exists())
            .andExpect(jsonPath("$.error").isEmpty());
    }
}