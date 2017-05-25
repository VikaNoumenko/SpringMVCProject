package controllers;/* 24.05.2017
ChatController @author Victoria Noumenko 
@version v1.0 */

import models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import services.ChatService;

import java.util.List;

import static javax.swing.text.StyleConstants.ModelAttribute;

@Controller("chatControllerAnnotation")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/chat/jsp", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("chatView");
        List<Chat> chats = chatService.findAll();
        result.addObject("chatModel", chats);
        return result;


        @RequestMapping(value = "chat/ftl", method = RequestMethod.GET)
        public String getAll(@ModelAttribute("model")ModelMap model) {
            List<Chat> chats = chatService.getAll();
            model.addAttribute("chatModel", chats);
            return "chatView";
        }
    }
}
