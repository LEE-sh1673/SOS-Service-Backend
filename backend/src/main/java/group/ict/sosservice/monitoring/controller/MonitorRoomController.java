package group.ict.sosservice.monitoring.controller;

import static group.ict.sosservice.common.utils.ApiUtils.success;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.ict.sosservice.common.utils.ApiUtils;
import group.ict.sosservice.monitoring.controller.dto.RoomViewRequest;
import group.ict.sosservice.monitoring.service.MonitorRoomService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class MonitorRoomController {

    private final MonitorRoomService monitorRoomService;

    @PostMapping
    public ApiUtils.ApiResult<?> findRoom(@Valid @RequestBody RoomViewRequest request) {
        return success(monitorRoomService.findUUIDByEmail(request.getEmail()));
    }
}
