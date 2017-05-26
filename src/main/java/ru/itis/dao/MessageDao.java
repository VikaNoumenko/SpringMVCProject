package dao;

import models.Message;

import java.util.List;

public interface MessageDao extends BaseDao<Message> {
    List<Message> findAllByChatId(int id);
}
