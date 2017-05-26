package controllers;/* 24.05.2017
MessageController @author Victoria Noumenko 
@version v1.0 */

import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ChatService;
import services.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static javax.swing.text.StyleConstants.ModelAttribute;

public class MessageController {

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/message/jsp", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("messageView");
        List<Message> message = messageService.findAll();
        result.addObject("messageModel", message);
        return result;


        @RequestMapping(value = "message/ftl", method = RequestMethod.GET)
        public String getAll(@ModelAttribute("model")ModelMap model) {
            List<Message> message = messageService.getAll();
            model.addAttribute("messageModel", message);
            return "messageView";
        }
    }


}

