package group.ict.sosservice.user.controller;

import static group.ict.sosservice.common.utils.ApiUtils.success;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.ict.sosservice.authentication.service.dto.UserPrincipal;
import group.ict.sosservice.common.utils.ApiUtils;
import group.ict.sosservice.user.controller.dto.SignUpRequest;
import group.ict.sosservice.user.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid final SignUpRequest request) {
        authService.signup(request.toServiceDto());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ApiUtils.ApiResult<?> me(@AuthenticationPrincipal final UserPrincipal userPrincipal) {
        return success(authService.findOne(userPrincipal.getUserId()));
    }
}
