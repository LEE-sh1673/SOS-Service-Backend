package group.ict.sosservice.user.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import group.ict.sosservice.common.annotations.AcceptanceTest;
import group.ict.sosservice.user.controller.dto.SignUpRequest;

@AutoConfigureMockMvc
@AcceptanceTest
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입한다.")
    void signup() throws Exception {
        final SignUpRequest signUpRequest = SignUpRequest.builder()
            .email("lsh901673@gmail.com")
            .password("1234")
            .name("이승훈")
            .build();

        final ResultActions result = mockMvc.perform(
            post("/auth/signup")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        );

        result.andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 요청 시 email 값은 필수이다.")
    void givenEmptyName_thenErrorResponse() throws Exception {
        final SignUpRequest signUpRequest = SignUpRequest.builder()
            .email(null)
            .password("1234")
            .name("이승훈")
            .build();

        final ResultActions result = mockMvc.perform(
            post("/auth/signup")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        );

        result.andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success", is(false)))
            .andExpect(jsonPath("$.error").exists())
            .andExpect(jsonPath("$.error.details").exists())
            .andExpect(jsonPath("$.error.details[0].message", is("이메일은 공백일 수 없습니다.")));
    }

    @Test
    @DisplayName("회원가입 요청 시 email, password, name 값은 필수이다.")
    void givenEmptyEmailAndPassword_thenErrorResponse() throws Exception {
        final SignUpRequest signUpRequest = SignUpRequest.builder().build();

        final ResultActions result = mockMvc.perform(
            post("/auth/signup")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        );

        result.andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success", is(false)))
            .andExpect(jsonPath("$.error").exists())
            .andExpect(jsonPath("$.error.details").exists())
            .andExpect(jsonPath("$.error.details.length()", is(3)));
    }

    @Test
    @DisplayName("회원가입 요청 시 email은 올바른 형식이어야 한다.")
    void givenInvalidEmail_thenErrorResponse() throws Exception {
        final SignUpRequest signUpRequest = SignUpRequest.builder()
            .email("invalid-email")
            .password("1234")
            .name(null)
            .build();

        final ResultActions result = mockMvc.perform(
            post("/auth/signup")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        );

        result.andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.success", is(false)))
            .andExpect(jsonPath("$.error").exists())
            .andExpect(jsonPath("$.error.details").exists())
            .andExpect(jsonPath("$.error.details.length()", is(2)));
    }
}