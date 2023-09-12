package group.ict.sosservice.user.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.ict.sosservice.authentication.service.dto.UserPrincipal;
import group.ict.sosservice.user.controller.dto.ChildRegisterRequest;
import group.ict.sosservice.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/child")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> registerChild(
        @RequestBody @Valid final ChildRegisterRequest request,
        @AuthenticationPrincipal final UserPrincipal userPrincipal
    ) {
        System.out.println("request = " + request);
        userService.registerChild(userPrincipal.getUserId(), request.getEmail());
        return ResponseEntity.ok().build();
    }
}
