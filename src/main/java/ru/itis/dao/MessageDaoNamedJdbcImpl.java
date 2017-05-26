package ru.itis.dao;

import ru.itis.models.Chat;
import ru.itis.models.Message;
import ru.itis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessageDaoNamedJdbcImpl implements MessageDao {
    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO message(text, chat_id, author_id) VALUES (:text, :chat, :author)";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE message SET text = :text WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND = "SELECT * FROM message WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM message";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM message WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND_ALL_BY_CHAT_ID = "SELECT * FROM message JOIN chat ON message.chat_id = chat.id WHERE chat.id = :id";


    private NamedParameterJdbcTemplate namedParameterTemplate;
    private UsersDao usersDao;
    private ChatDao chatDao;

    @Autowired
    public MessageDaoNamedJdbcImpl(DataSource dataSource) {
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public MessageDaoNamedJdbcImpl(DataSource dataSource, UsersDao usersDao, ChatDao chatDao) {
        this.usersDao = usersDao;
        this.chatDao = chatDao;
        this.namedParameterTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Message> messageRowMapper = new RowMapper<Message>() {
        public Message mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = usersDao.find(resultSet.getInt("author_id"));
            Chat chat = chatDao.find(resultSet.getInt("chat_id"));
            Message message = new Message.Builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .chat(chat)
                    .author(user)
                    .build();
            return message;
        }
    };

    public Message find(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return (Message) namedParameterTemplate.query(SQL_FIND, params, messageRowMapper);
    }

    public int save(Message message) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("text", message.getText())
                .addValue("chat", message.getChat())
                .addValue("author", message.getAuthor());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedParameterTemplate.update(SQL_SAVE, params, holder, new String[]{"id"});
        Number generated = holder.getKey();
        return generated.intValue();
    }

    public void delete(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        namedParameterTemplate.update(SQL_DELETE, params);
    }

    public List<Message> findAll() {
        return namedParameterTemplate.query(SQL_FIND_ALL, messageRowMapper);
    }

    public void update(Message message) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", message.getText());
        namedParameterTemplate.update(SQL_UPDATE, params);
    }

    @Override
    public List<Message> findAllByChatId(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterTemplate.query(SQL_FIND_ALL_BY_CHAT_ID, messageRowMapper);
    }
}
