package ru.itis.dao;

import ru.itis.models.Chat;
import ru.itis.models.Message;
import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChatDaoNamedJdbcImpl implements ChatDao {

    //language=SQL
    private static final String SQL_FIND = "SELECT * FROM chat WHERE id = :id";

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT chat.id AS chat_id, chat.name AS chat_name, message.id AS message_id," +
            " message.text AS message_text, message.chat_id AS message_chat_id\n" +
            "FROM chat\n" +
            "  JOIN service_user\n" +
            "    ON service_user.id = creator_id\n" +
            "  JOIN message\n" +
            "    ON message.chat_id = chat.id\n" +
            "  JOIN chat_user\n" +
            "    ON service_user.id = chat_user.user_id ORDER BY chat.id;";


    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO chat(name, creator_id) VALUES (:name, :creator)";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM chat WHERE id = :id";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE chat SET name = :name,:creator_id = :creator WHERE id = :id";

    private NamedParameterJdbcTemplate namedParameterTemplate;
    private UsersDao usersDao;
    private MessageDao messageDao;

    @Autowired
    public ChatDaoNamedJdbcImpl(DataSource dataSource) {
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Chat> chatRowMapper = new RowMapper<Chat>() {
        public Chat mapRow(ResultSet resultSet, int i) throws SQLException {
            User creator = usersDao.find(resultSet.getInt("creator_id"));
            Chat chat = new Chat.Builder()
                    .id(resultSet.getInt("id"))
                    .creator(creator)
                    .name(resultSet.getString("name"))
                    .users((List<User>) resultSet.getObject("users"))
                    .messages((List<Message>) resultSet.getObject("messages"))
                    .build();
            return chat;
        }
    };

    public ResultSetExtractor<List<Chat>> resultSetExtractor = new ResultSetExtractor<List<Chat>>() {
        @Override
        public List<Chat> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            // уникальные чаты
            Map<Integer, Chat> chats = new HashMap<>();
            // все уникальные сообщения
            Map<Integer, Message> messages = new HashMap<>();
            Map<String, Chat> chatNames = new HashMap<>();
            Map<String, Message> messageTexts = new HashMap<>();
            // смотрим текущую строку
            while (resultSet.next()) {
                // достаем id чата
                int chatId = resultSet.getInt("chat_id");

                // если такого чата еще не было
                if (chats.get(chatId) == null) {
                    // создаем чат
                    String chatName = resultSet.getString("chat_name");
                    Chat chat = new Chat.Builder()
                            .id(chatId)
                            .name(chatName)
                            .messages(new ArrayList<Message>())
                            .build();
                    // кладем в мап
                    chats.put(chatId, chat);
                    // таким образом после прохождения всего while у нас
                    // в мапе будут все чаты, доступные по id
                }
                // достаем id сообщения
                int messageId = resultSet.getInt("message_id");

                if (messages.get(messageId) == null) {
                    String messageText = resultSet.getString("message_text");
                    int messageChatId = resultSet.getInt("message_chat_id");
                    Message message = new Message.Builder()
                            .text(messageText)
                            .id(messageId).build();
                    messages.put(messageId, message);
                    chats.get(messageChatId).getMessages().add(message);
                }

                String chatName = resultSet.getString("chat_name");

                if (chatNames.get(chatName) == null) {
                    int idOfChat = resultSet.getInt("chat_id");
                    Chat chat = new Chat.Builder()
                            .id(chatId)
                            .name(chatName)
                            .messages(new ArrayList<Message>())
                            .build();
                    chatNames.put(chatName, chat);
                }

                String messageText = resultSet.getString("message_text");

                if (messageTexts.get(messageText) == null) {
                    int messageChatId = resultSet.getInt("message_chat_id");
                    int id = resultSet.getInt("id");
                    Message message = new Message.Builder()
                            .text(messageText)
                            .id(messageId).build();
                    messageTexts.put(messageText, message);

                }

                int messageChatId = resultSet.getInt("chat_id");

                if (chats.get(messageChatId) == null) {
                    int idOfChat = resultSet.getInt("chat_id");
                    Chat chat = new Chat.Builder()
                            .id(chatId)
                            .name(chatName)
                            .messages(new ArrayList<Message>())
                            .build();
                    chats.put(messageChatId, chat);
                }
            }
            return new ArrayList<Chat>(chats.values());
        }
    };

    public Chat find(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return (Chat) namedParameterTemplate.query(SQL_FIND, params, chatRowMapper);
    }

    public int save(Chat chat) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("creator", chat.getCreator())
                .addValue("name", chat.getName());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedParameterTemplate.update(SQL_SAVE, params, holder, new String[]{"id"});
        Number generatedId = holder.getKey();
        return generatedId.intValue();
    }

    public void delete(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedParameterTemplate.update(SQL_DELETE, params);
    }

    public List<Chat> findAll() {
        return namedParameterTemplate.query(SQL_FIND_ALL, resultSetExtractor);
    }

    public void update(Chat chat) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", chat.getName());
        params.put("creator", chat.getCreator());
        namedParameterTemplate.update(SQL_UPDATE, params);
    }
}