package com.naived.sparkpm25b.controller;

import com.naived.sparkpm25b.websocket.PMInfoWebsocket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PMReceiverController {

    @PostMapping(value = "/submit")
    public void submitPMInfo(@RequestBody String info){
        System.out.println(info);
        PMInfoWebsocket.sendAll(info);
    }

}
