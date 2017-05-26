package services.impl;


import dao.ChatDao;
import models.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ChatService;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatDao chatDao;

    public ChatServiceImpl(ChatDao chatDao) {
        this.chatDao = chatDao;
    }


    @Override
    public Chat find(int id) {
        return chatDao.find(id);
    }

    @Override
    public int save(Chat model) {
        return chatDao.save(model);
    }

    @Override
    public void update(Chat model) {
        chatDao.update(model);
    }

    @Override
    public void delete(int id) {
        chatDao.delete(id);
    }

    @Override
    public List<Chat> findAll() {
        return chatDao.findAll();
    }
}
