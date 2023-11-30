package com.example.demo;

import org.springframework.modulith.events.Externalized;

@Externalized("http://localhost:4566/000000000000/test-queue-name")
public record EventExternalized(String id, String name) {
}
