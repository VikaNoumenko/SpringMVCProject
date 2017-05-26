package ru.itis.dao;

import ru.itis.models.Message;

import java.util.List;

public interface MessageDao extends BaseDao<Message> {
    List<Message> findAllByChatId(int id);
}
