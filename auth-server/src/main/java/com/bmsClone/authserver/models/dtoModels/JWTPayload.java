package com.bmsClone.authserver.models.dtoModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTPayload {
    private String id;
    private Boolean admin;
}
