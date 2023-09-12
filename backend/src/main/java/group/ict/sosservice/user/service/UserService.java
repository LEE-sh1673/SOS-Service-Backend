package group.ict.sosservice.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.ict.sosservice.common.exception.ErrorType;
import group.ict.sosservice.user.exception.InvalidMemberException;
import group.ict.sosservice.user.model.Email;
import group.ict.sosservice.user.model.User;
import group.ict.sosservice.user.model.UserRepository;
import group.ict.sosservice.user.service.dto.ChildResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public void registerChild(final Long parentId, final String childEmail) {
        final User parent = findById(parentId);

        final User child = userRepository.findByEmail(Email.of(childEmail))
            .orElseThrow(() -> new InvalidMemberException(ErrorType.NOT_FOUND_MEMBER));

        parent.updateChild(child);
    }

    @Transactional(readOnly = true)
    public ChildResponseDto findChild(final Long parentId) {
        final User child = findById(parentId).getChild();

        if (child == null) {
            return ChildResponseDto.empty();
        }
        return modelMapper.map(child, ChildResponseDto.class);
    }

    private User findById(final Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new InvalidMemberException(ErrorType.NOT_FOUND_MEMBER));
    }
}