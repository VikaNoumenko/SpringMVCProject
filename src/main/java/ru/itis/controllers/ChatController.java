package ru.itis.controllers;/* 24.05.2017
ChatController @author Victoria Noumenko 
@version v1.0 */

import ru.itis.models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.itis.services.ChatService;

import java.util.List;

@Controller("chatControllerAnnotation")
public class ChatController {

    @Autowired
    ChatService chatService;

    @RequestMapping(value = "/views/jsp/chatView", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("chatView");
        List<Chat> chat = chatService.findAll();
        result.addObject("chatModel", chat);
        return result;
    }

    @RequestMapping(value = "/views/ftl/chatView", method = RequestMethod.GET)
    public String getAll(@ModelAttribute("model")ModelMap model) {
        List<Chat> chats = chatService.findAll();
        model.addAttribute("chatModel", chats);
        return "chatView";
    }
}
