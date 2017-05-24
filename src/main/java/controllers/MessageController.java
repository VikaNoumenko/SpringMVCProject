package controllers;/* 24.05.2017
MessageController @author Victoria Noumenko 
@version v1.0 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import services.ChatService;
import services.MessageService;

public class MessageController {

    @Autowired
    private MessageService messageService;




}

