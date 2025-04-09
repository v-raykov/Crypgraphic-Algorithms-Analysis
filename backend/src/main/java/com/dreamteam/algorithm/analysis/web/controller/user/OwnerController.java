package com.dreamteam.algorithm.analysis.web.controller.user;

import com.dreamteam.algorithm.analysis.model.User;
import com.dreamteam.algorithm.analysis.web.service.user.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        ownerService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}/adminStatus/{isAdmin}")
    public ResponseEntity<Void> toggleUserAdmin(@PathVariable String id, @PathVariable boolean isAdmin) {
        ownerService.toggleUserAdmin(id, isAdmin);
        return ResponseEntity.noContent().build();
    }
}
