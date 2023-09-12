package group.ict.sosservice.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.ict.sosservice.common.exception.ErrorType;
import group.ict.sosservice.user.exception.InvalidMemberException;
import group.ict.sosservice.user.model.Email;
import group.ict.sosservice.user.model.User;
import group.ict.sosservice.user.model.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void registerChild(final Long parentId, final String childEmail) {
        final User parent = userRepository.findById(parentId)
            .orElseThrow(() -> new InvalidMemberException(ErrorType.NOT_FOUND_MEMBER));

        final User child = userRepository.findByEmail(Email.of(childEmail))
            .orElseThrow(() -> new InvalidMemberException(ErrorType.NOT_FOUND_MEMBER));

        parent.updateChild(child);
    }
}
