package User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private final String name;
    private final List<String> friends = new ArrayList<>();
    private Map<String, String> messages = new HashMap<>();
    private Map<String, String> sendMessages = new HashMap<>();

    public User(String name) {
        this.name = name;
    }

    public void addFriend(String friend) {
        this.friends.add(friend);
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {

        this.messages = messages;
    }

    public Map<String, String> getSendMessages() {
        return sendMessages;
    }

    public void setSendMessages(Map<String, String> sendMessages) {
        this.sendMessages = sendMessages;
    }

    public void addMessage(String User, String msg) {
        this.messages.put(User, msg);
    }


    public void sendMessage(String User, String msg) {
        this.sendMessages.put(User, msg);
    }

    public List<String> getFriends() {
        return friends;
    }

    public String getName() {
        return name;
    }
}
