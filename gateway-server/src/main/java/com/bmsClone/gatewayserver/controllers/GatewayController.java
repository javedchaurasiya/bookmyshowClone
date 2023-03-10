package com.bmsClone.gatewayserver.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequiredArgsConstructor
public class GatewayController {

    private final Environment env;

    @GetMapping("/health")
    public String getHealth() {
//        System.out.println("Hitting Endpoint");
        return "GatewayPort : " + env.getProperty("local.server.port");
    }
}
