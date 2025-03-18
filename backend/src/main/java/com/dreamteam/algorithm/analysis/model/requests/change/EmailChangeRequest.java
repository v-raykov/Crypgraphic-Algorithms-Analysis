package com.dreamteam.algorithm.analysis.model.requests.change;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailChangeRequest extends ChangeRequest {
    private String email;
}
