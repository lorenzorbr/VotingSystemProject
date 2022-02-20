package com.tests.demo.controller;

import com.tests.demo.shared.model.Schedule;
import com.tests.demo.shared.service.SessionService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessao")
public class SessionController {

    SessionService service;

    public SessionController(SessionService service){
        this.service = service;
    }

    @PutMapping()
    public Schedule createSession(@RequestBody Schedule schedule){return service.createSession(schedule);}

}
