package com.jh.firstservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/first-service")
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to First Service!";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        System.out.println(header);
        return "Hello World in First Service";
    }

    @GetMapping("/check")
    public String check(@RequestHeader Map<String, String> headers) {
        System.out.println("Headers received at FirstServiceController /check endpoint:");
        System.out.println(headers);
        StringBuilder claimsStringBuilder = new StringBuilder("JWT Claims: ");
        headers.forEach((key, value) -> {
            if (key.startsWith("x-claim-")) {
                System.out.println(key + ": " + value);
                claimsStringBuilder.append(key).append(": ").append(value).append(", ");
            }
        });

        String claimsString = claimsStringBuilder.toString();
        if (claimsString.endsWith(", ")) {
            claimsString = claimsString.substring(0, claimsString.length() - 2);
        }

        return "Hi, there, This is a message from First Service with JWT claims. " + claimsString;
    }
}
