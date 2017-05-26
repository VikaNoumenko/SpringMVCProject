package ru.itis.services;

import ru.itis.models.Chat;

import java.util.List;

/**
 * 06.05.2017
 * ChatService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface ChatService {

    Chat find(int id);
    int save(Chat model);
    void update(Chat model);
    void delete(int id);
    List<Chat> findAll();
}
