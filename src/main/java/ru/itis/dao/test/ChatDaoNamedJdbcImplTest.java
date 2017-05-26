package ru.itis.dao.test;

import ru.itis.dao.ChatDaoNamedJdbcImpl;
import ru.itis.models.Chat;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChatDaoNamedJdbcImplTest {

    private ChatDaoNamedJdbcImpl chatDaoNamedJdbcImpl;

    private final int CHAT_1_ID = 1;
    private final int CHAT_2_ID = 2;

    private final Chat CHAT_1 = new Chat.Builder()
            .id(CHAT_1_ID)
//            .creator(1)
            .name("ChatName2")
            .build();

    private final Chat CHAT_2 = new Chat.Builder()
            .id(CHAT_2_ID)
//            .creator(2)
            .name("ChatName")
            .build();

    @Before
    public void testSetUp() throws Exception {
        GenericXmlApplicationContext context = new  GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("test");
        context.load("ru.itis\\spring\\context.xml");
        context.refresh();
        chatDaoNamedJdbcImpl = context.getBean(ChatDaoNamedJdbcImpl.class);
    }

    @Test
    public void testFind() throws Exception {
        Chat expected = CHAT_1;
        Chat actual = chatDaoNamedJdbcImpl.find(CHAT_1_ID);

        assertEquals(expected, actual);
    }

    @Test
    public void testSave() throws Exception {
        Chat expected = CHAT_2;
        int id = chatDaoNamedJdbcImpl.save(CHAT_2);
        Chat actual = chatDaoNamedJdbcImpl.find(CHAT_2_ID);

        assertEquals(expected,actual);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() throws Exception {
        chatDaoNamedJdbcImpl.delete(CHAT_1_ID);
        chatDaoNamedJdbcImpl.find(CHAT_1_ID);

    }

    @Test
    public void testFindAll() throws Exception {
        List<Chat> expected = new ArrayList<>();
        expected.add(CHAT_1);
        expected.add(CHAT_2);
        List<Chat> actual = chatDaoNamedJdbcImpl.findAll();

        assertEquals(expected,actual);
    }

    @Test
    public void testUpdate() throws Exception {
        Chat expected = CHAT_1;
        chatDaoNamedJdbcImpl.update(CHAT_1);
        Chat actual = chatDaoNamedJdbcImpl.find(CHAT_1_ID);

        assertEquals(expected, actual);
    }

}