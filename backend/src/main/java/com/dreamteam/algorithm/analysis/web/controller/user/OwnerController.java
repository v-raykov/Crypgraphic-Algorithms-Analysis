package com.dreamteam.algorithm.analysis.web.controller.user;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.web.service.user.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public ResponseEntity<Void> dashboard() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(ownerService.getUsers());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        ownerService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}/is-admin/{isAdmin}")
    public ResponseEntity<Void> toggleUserAdmin(@PathVariable UUID id, @PathVariable boolean isAdmin) {
        ownerService.toggleUserAdmin(id, isAdmin);
        return ResponseEntity.noContent().build();
    }
}
