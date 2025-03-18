package com.dreamteam.algorithm.analysis.model.requests.change;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PasswordChangeRequest extends ChangeRequest {
    private String newPassword;
}
