package ru.itis.dao;

import ru.itis.models.Chat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.List;

/**
 * 16.05.2017
 * Main
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis\\spring\\context.xml");
        ChatDao chatDao = new ChatDaoNamedJdbcImpl(context.getBean(DataSource.class));
        List<Chat> chatList = chatDao.findAll();
    }
}
