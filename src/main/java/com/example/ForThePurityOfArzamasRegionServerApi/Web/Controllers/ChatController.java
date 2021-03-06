package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.MessageRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("method")
public class ChatController {

    @Autowired
    EventRepository userRepository;


    @PostMapping("chats.getMessages")
    public @ResponseBody ArrayList<MessageResponse> getMessages(@RequestParam("chat_id") String chat_id, @RequestParam(value = "count", required = false) String count) {

        return null;
    }

    @PostMapping("chats.createMessage")
    public @ResponseBody String createMessage(@RequestParam("chat_id") String chat_id, @RequestBody MessageRequest message) {

        return null;
    }

    @PostMapping("chats.deleteMessage")
    public @ResponseBody String deleteMessageById(@RequestParam("user_deleter_id") String user_deleter_id, @RequestParam("message_id") String message_id) {

        return null;
    }
}
