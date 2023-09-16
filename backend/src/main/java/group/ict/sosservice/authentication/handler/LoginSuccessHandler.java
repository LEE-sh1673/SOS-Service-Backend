package group.ict.sosservice.authentication.handler;

import static group.ict.sosservice.common.utils.ApiUtils.success;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import group.ict.sosservice.authentication.service.dto.UserPrincipal;
import group.ict.sosservice.common.utils.ApiUtils;
import group.ict.sosservice.monitoring.service.MonitorRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    private final MonitorRoomService monitorRoomService;

    @Override
    public void onAuthenticationSuccess(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Authentication authentication
    ) throws IOException {
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        log.info("[인증성공] user = {}", principal.getUsername());
        final String roomUUID = monitorRoomService.createRoom(principal.getUsername());
        sendResponse(response, success(roomUUID));
    }

    private void sendResponse(
        final HttpServletResponse response,
        final ApiUtils.ApiResult<?> apiResult
    ) throws IOException {
        response.setCharacterEncoding(UTF_8.displayName());
        response.setStatus(SC_OK);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(apiResult));
    }
}