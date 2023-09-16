package group.ict.sosservice.monitoring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.ict.sosservice.common.exception.BadRequestException;
import group.ict.sosservice.common.exception.ErrorType;
import group.ict.sosservice.monitoring.model.MonitorRoom;
import group.ict.sosservice.monitoring.model.MonitorRoomRepository;
import group.ict.sosservice.user.exception.InvalidMemberException;
import group.ict.sosservice.user.model.Email;
import group.ict.sosservice.user.model.User;
import group.ict.sosservice.user.model.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MonitorRoomService {

    private final MonitorRoomRepository roomRepository;

    private final UserRepository userRepository;

    @Transactional
    public String createRoom(final String email) {
        final User user = findUserByEmail(email);
        validateRoom(user);

        final MonitorRoom room = MonitorRoom.builder()
            .user(user)
            .build();

        return roomRepository.save(room).getRoomUUID();
    }

    private void validateRoom(final User user) {
        if (roomRepository.existsMonitorRoomByUser(user)) {
            throw new BadRequestException(ErrorType.DUPLICATED_ROOM);
        }
    }

    public String findUUIDByEmail(final String email) {
        final User user = findUserByEmail(email);

        final MonitorRoom monitorRoom = roomRepository.findByUser(user)
            .orElseThrow(() -> new BadRequestException(ErrorType.NOT_FOUND_ROOM));

        return monitorRoom.getRoomUUID();
    }

    private User findUserByEmail(final String email) {
        return userRepository.findByEmail(Email.of(email))
            .orElseThrow(() -> new InvalidMemberException(ErrorType.NOT_FOUND_MEMBER));
    }
}
