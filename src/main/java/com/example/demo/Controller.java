package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final Service service;

    @GetMapping("listener")
    public void listener(

    ) {
        service.withListener();
    }

    @GetMapping("noListener")
    public void noListener(

    ) {
        service.noListener();
    }

    @GetMapping("externalized")
    public void externalized(

    ) {
        service.externalized();
    }
}
