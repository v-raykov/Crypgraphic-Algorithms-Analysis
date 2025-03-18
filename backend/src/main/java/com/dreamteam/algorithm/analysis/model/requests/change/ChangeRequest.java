package com.dreamteam.algorithm.analysis.model.requests.change;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ChangeRequest {
    private String password;
}
