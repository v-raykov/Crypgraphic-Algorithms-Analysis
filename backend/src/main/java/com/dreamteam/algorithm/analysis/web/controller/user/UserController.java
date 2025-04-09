package com.dreamteam.algorithm.analysis.web.controller.user;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.model.dto.UserDto;
import com.dreamteam.algorithm.analysis.model.requests.change.EmailChangeRequest;
import com.dreamteam.algorithm.analysis.model.requests.change.PasswordChangeRequest;
import com.dreamteam.algorithm.analysis.model.requests.change.UsernameChangeRequest;
import com.dreamteam.algorithm.analysis.web.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> userInformation(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok().body(userService.getUserInformation(user));
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequest request, @AuthenticationPrincipal User user) {
        LoggerFactory.getLogger(UserController.class).info(request.getPassword());
        userService.changePassword(request, user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-email")
    public ResponseEntity<Void> changeEmail(@RequestBody EmailChangeRequest request, @AuthenticationPrincipal User user) {
        userService.changeEmail(request, user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-username")
    public ResponseEntity<Void> changeUsername(@RequestBody UsernameChangeRequest request, @AuthenticationPrincipal User user) {
        userService.changeUsername(request, user);
        return ResponseEntity.ok().build();
    }
}
