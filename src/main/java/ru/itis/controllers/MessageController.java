package ru.itis.controllers;/* 24.05.2017
MessageController @author Victoria Noumenko 
@version v1.0 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.itis.models.Message;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.MessageService;

import java.util.List;

@Controller("messageControllerAnnotation")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/views/jsp/messageView", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("messageView");
        List<Message> message = messageService.findAll();
        result.addObject("messageModel", message);
        return result;


//        @RequestMapping(value = "/views/ftl/messageView", method = RequestMethod.GET)
//        public String getAll(@ModelAttribute("model")ModelMap model) {
//            List<Message> messages = messageService.findAll();
//            model.addAttribute("messageModel", messages);
//            return "messageView";
//        }
    }


}

