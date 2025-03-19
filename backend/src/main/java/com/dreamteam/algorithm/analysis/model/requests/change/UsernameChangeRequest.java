package com.dreamteam.algorithm.analysis.model.requests.change;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsernameChangeRequest extends ChangeRequest {
    private String newUsername;
}
