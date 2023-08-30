package group.ict.sosservice.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import group.ict.sosservice.common.annotations.AcceptanceTest;
import group.ict.sosservice.common.exception.ErrorType;
import group.ict.sosservice.user.domain.Email;
import group.ict.sosservice.user.domain.Role;
import group.ict.sosservice.user.domain.User;
import group.ict.sosservice.user.domain.UserRepository;
import group.ict.sosservice.user.exception.InvalidMemberException;
import group.ict.sosservice.user.service.dto.SignUpRequestDto;

@AcceptanceTest
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    @DisplayName("회원가입한다.")
    void signup() {
        final SignUpRequestDto request = SignUpRequestDto.builder()
            .name("name-1")
            .password("password-1")
            .email("lsh@gmail.com")
            .build();

        authService.signup(request);

        final User user = userRepository.findAll().get(0);
        assertEquals(1L, userRepository.count());
        assertEquals(Email.of(request.getEmail()), user.getEmail());
        assertTrue(encoder.matches(request.getPassword(), user.getPassword()));
        assertEquals(request.getName(), user.getName());
    }

    @Test
    @DisplayName("이메일 형식이 올바르지 않으면 오류가 발생한다.")
    void givenInvalidEmail_thenThrowException() {
        final SignUpRequestDto request = SignUpRequestDto.builder()
            .name("name-1")
            .password("password-1")
            .email("@gmail.com")
            .build();

        Assertions.assertThatThrownBy(() -> authService.signup(request))
            .isInstanceOf(InvalidMemberException.class)
            .hasMessageContaining(ErrorType.INVALID_MEMBER_EMAIL.getMessage());
    }

    @Test
    @DisplayName("이메일이 중복된 경우 오류가 발생한다.")
    void givenDuplicatedCredential_thenThrowException() {

        userRepository.save(User.builder()
            .name("name-1")
            .password(encoder.encode("password-1"))
            .email("lsh@gmail.com")
            .role(Role.USER)
            .build());

        final SignUpRequestDto request = SignUpRequestDto.builder()
            .name("name-1")
            .password("password-1")
            .email("lsh@gmail.com")
            .build();

        Assertions.assertThatThrownBy(() -> authService.signup(request))
            .isInstanceOf(InvalidMemberException.class)
            .hasMessageContaining(ErrorType.DULICATED_MEMBER_EMAIL.getMessage());
    }
}