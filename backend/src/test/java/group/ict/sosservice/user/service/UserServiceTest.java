package group.ict.sosservice.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import group.ict.sosservice.common.annotations.AcceptanceTest;
import group.ict.sosservice.user.model.Role;
import group.ict.sosservice.user.model.User;
import group.ict.sosservice.user.model.UserRepository;

@AcceptanceTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    @DisplayName("보호 대상자를 등록할 수 있다.")
    void register_child() {
        final User parent = userRepository.save(User.builder()
            .name("parent")
            .password(encoder.encode("password-1"))
            .email("parent@gmail.com")
            .role(Role.USER)
            .build()
        );

        final User child = userRepository.save(User.builder()
            .name("child")
            .password(encoder.encode("password-1"))
            .email("child@gmail.com")
            .role(Role.USER)
            .build()
        );

        userService.registerChild(parent.getId(), child.getEmail().getValue());

        final User actual = parent.getChild();
        assertEquals(child.getName(), actual.getName());
        assertEquals(child.getBirth(), actual.getBirth());
        assertEquals(child.getPassword(), actual.getPassword());
        assertEquals(child.getEmail(), actual.getEmail());
    }
}