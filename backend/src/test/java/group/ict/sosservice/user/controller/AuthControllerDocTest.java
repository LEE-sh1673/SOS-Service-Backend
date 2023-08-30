package group.ict.sosservice.user.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.OBJECT;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import group.ict.sosservice.common.annotations.AcceptanceTest;
import group.ict.sosservice.user.controller.dto.SignUpRequest;

@AcceptanceTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(
    uriScheme = "https",
    uriHost = "api.ict-sos-service.com",
    uriPort = 443
)
public class AuthControllerDocTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입")
    void signup() throws Exception {
        final SignUpRequest signUpRequest = SignUpRequest.builder()
            .name("홍길동")
            .email("test-user@gmail.com")
            .password("test-password")
            .birth(LocalDate.of(1997, 4, 23))
            .profileImage("www.test.com/profile.jpg")
            .phoneNumber("010-1234-5678")
            .build();

        final ResultActions result = mockMvc.perform(
            post("/api/v1/auth/signup")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        );

        result.andDo(print())
            .andExpect(status().isOk())
            .andDo(document("user-signup",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("name").description("회원 이름"),
                    fieldWithPath("email").description("이메일 주소")
                        .attributes(key("constraint").value(
                            "형식에 맞춰 작성 (^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$)")),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("birth")
                        .description("생년월일").optional()
                        .attributes(key("constraint").value("형식에 맞춰 작성 (yyyy-MM-dd)")),
                    fieldWithPath("profileImage").description("프로필 URL").optional(),
                    fieldWithPath("phoneNumber")
                        .description("전화번호").optional()
                        .attributes(key("constraint").value("형식에 맞춰 작성 (01x-xxx(x)-xxxx) "))
                )
            ));
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
            post("/api/v1/auth/signup")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest))
        );

        result.andDo(print())
            .andExpect(status().isBadRequest())
            .andDo(document("user-signup-invalid-email",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(
                    fieldWithPath("name").description("회원 이름"),
                    fieldWithPath("email").description("이메일 주소")
                        .attributes(key("constraint").value(
                            "형식에 맞춰 작성 (^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}$)")),
                    fieldWithPath("password").description("비밀번호"),
                    fieldWithPath("birth")
                        .description("생년월일").optional()
                        .attributes(key("constraint").value("형식에 맞춰 작성 (yyyy-MM-dd)")),
                    fieldWithPath("profileImage").description("프로필 URL").optional(),
                    fieldWithPath("phoneNumber")
                        .description("전화번호").optional()
                        .attributes(key("constraint").value("형식에 맞춰 작성 (01x-xxx(x)-xxxx) "))
                ),
                responseFields(
                    fieldWithPath("success").description("응답 성공 여부"),
                    fieldWithPath("response").type(OBJECT).description("응답 객체").optional(),
                    fieldWithPath("error").type(OBJECT).ignored(),
                    fieldWithPath("error.message").description("오류 메시지"),
                    fieldWithPath("error.details").type(ARRAY).description("오류 필드 목록").optional(),
                    fieldWithPath("error.details[].param").description("오류 핃드명").optional(),
                    fieldWithPath("error.details[].message").description("오류 필드 상세 메시지").optional()
                )
            ));
    }
}
