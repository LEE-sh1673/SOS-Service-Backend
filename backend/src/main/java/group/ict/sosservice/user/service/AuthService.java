package group.ict.sosservice.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.ict.sosservice.common.exception.ErrorType;
import group.ict.sosservice.user.domain.Email;
import group.ict.sosservice.user.domain.Role;
import group.ict.sosservice.user.domain.User;
import group.ict.sosservice.user.domain.UserRepository;
import group.ict.sosservice.user.exception.InvalidMemberException;
import group.ict.sosservice.user.service.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(final SignUpRequestDto request) {
        validateCredentials(request);
        userRepository.save(mapToUser(request));
    }

    private void validateCredentials(final SignUpRequestDto request) {
        final Email email = Email.of(request.getEmail());

        if (userRepository.existsUserByEmail(email)) {
            throw new InvalidMemberException(ErrorType.DULICATED_MEMBER_EMAIL);
        }
    }

    private User mapToUser(final SignUpRequestDto request) {
        return User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(encodePassword(request.getPassword()))
            .birth(request.getBirth())
            .profileImage(request.getProfileImage())
            .phoneNumber(request.getPhoneNumber())
            .role(Role.USER)
            .build();
    }

    private String encodePassword(final String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
