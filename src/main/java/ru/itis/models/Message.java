package ru.itis.models;

/**
 * 06.05.2017
 * Message
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Message {

    private int id;
    private String text;
    private Chat chat;
    private User author;

    public Message(Builder builder){
        this.id = builder.id;
        this.text = builder.text;
        this.chat = builder.chat;
        this.author = builder.author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public static class Builder {

        private int id;
        private String text;
        private Chat chat;
        private User author;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder chat(Chat chat) {
            this.chat = chat;
            return this;
        }

        public Builder author(User author) {
            this.author = author;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

        @Override
        public String toString() {
            return id + " "
                    + text + " "
                    + chat + " "
                    + author;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj != null && obj instanceof Message){
                Message that = (Message)obj;
                return this.id == that.id
                        && this.text.equals(that.text)
                        && this.chat.equals(that.chat)
                        && this.author.equals(that.author);
            } return false;
        }
    }
}
