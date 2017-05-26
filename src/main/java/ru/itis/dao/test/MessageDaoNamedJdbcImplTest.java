package dao.test;

import dao.MessageDaoNamedJdbcImpl;
import models.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MessageDaoNamedJdbcImplTest {

    private MessageDaoNamedJdbcImpl messageDaoNamedJdbcImpl;

    private final int MESSAGE_1_ID = 1;
    private final int MESSAGE_2_ID = 2;

    private final Message MESSAGE_1 = new Message.Builder()
            .id(MESSAGE_1_ID)
            .text("SomeText")
//            .chat(1)
//            .author(1)
            .build();

    private final Message MESSAGE_2 = new Message.Builder()
            .id(MESSAGE_2_ID)
            .text("SomeText2")
//            .chat(2)
//            .author(2)
            .build();

    @Before
    public void testSetUp() throws Exception {
        GenericXmlApplicationContext context = new  GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("test");
        context.load("ru.itis\\spring\\context.xml");
        context.refresh();
        messageDaoNamedJdbcImpl = context.getBean(MessageDaoNamedJdbcImpl.class);

    }

    @Test
    public void testFind() throws Exception {
        Message expected = MESSAGE_1;
        Message actual = messageDaoNamedJdbcImpl.find(MESSAGE_1_ID);

        assertEquals(expected, actual);
    }

    @Test
    public void testSave() throws Exception {
        Message expected =  MESSAGE_2;
        int id = messageDaoNamedJdbcImpl.save(MESSAGE_2);
        Message actual = messageDaoNamedJdbcImpl.find(MESSAGE_2_ID);

        assertEquals(expected,actual);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() throws Exception {
        messageDaoNamedJdbcImpl.delete(MESSAGE_1_ID);
        messageDaoNamedJdbcImpl.find(MESSAGE_1_ID);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Message> expected = new ArrayList<>();
        expected.add(MESSAGE_1);
        expected.add(MESSAGE_2);
        List<Message> actual = messageDaoNamedJdbcImpl.findAll();

        assertEquals(expected,actual);


    }

    @Test
    public void testUpdate() throws Exception {
        Message expected = MESSAGE_1;
        messageDaoNamedJdbcImpl.update(MESSAGE_1);
        Message actual = messageDaoNamedJdbcImpl.find(MESSAGE_1_ID);

        assertEquals(expected, actual);
    }

    @Test
    public void testFindAllByChatId() throws Exception {
        List<Message> expected = new ArrayList<Message>();
        expected.add(MESSAGE_1);
        List<Message> actual = messageDaoNamedJdbcImpl.findAllByChatId(MESSAGE_1_ID);

        assertEquals(expected, actual);
    }

}