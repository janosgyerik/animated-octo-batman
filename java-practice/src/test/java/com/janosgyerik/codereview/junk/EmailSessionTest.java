package com.janosgyerik.codereview.junk;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSessionTest {
}

class MyProps {

    public static String getProperty(String s) {
        return s;
    }
}

class EmailSession {

    private static final Logger LOGGER = Logger.getLogger(EmailSession.class.getName());

    private Store store;
    private Folder inbox;
    private int messageCount;
    private List<Message> unreadMessages;

    public EmailSession() {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", MyProps.getProperty("mail.store.protocol"));
        Session session = Session.getInstance(props, null);
        try {
            store = session.getStore();
            store.connect(MyProps.getProperty("mail.host"), MyProps.getProperty("mail.user"), MyProps.getProperty("mail.password"));
            inbox = store.getFolder(MyProps.getProperty("mail.foldername"));
            inbox.open(Folder.READ_ONLY);
            messageCount = inbox.getMessageCount();
            List<Message> messages = getMessages(inbox, messageCount);
            inbox.close(true);
            unreadMessages = getUnreadMessages(messages);
        } catch (NoSuchProviderException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private List<Message> getMessages(Folder inbox, int messageCount) throws MessagingException {
        if (messageCount > 100) {
            LOGGER.warning("More than 100 messages");
            return Arrays.asList(inbox.getMessages(1, 100));
        }
        return Arrays.asList(inbox.getMessages());
    }

    private List<Message> getUnreadMessages(List<Message> messages) throws MessagingException {
        List<Message> unreadMessages = new ArrayList<>();
        for (Message message : messages) {
            boolean isUnreadMessage = true;
            for (Flags.Flag flag : message.getFlags().getSystemFlags()) {
                if (flag == Flags.Flag.SEEN) {
                    isUnreadMessage = false;
                    break;
                }
            }
            if (isUnreadMessage) {
                unreadMessages.add(message);
            }
        }
        return unreadMessages;
    }

    public Folder getInbox() {
        return inbox;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public int getUnreadMessageCount() {
        return unreadMessages.size();
    }

    public List<Message> getUnreadMessages() {
        return unreadMessages;
    }

    public boolean close() {
        try {
            store.close();
            return true;
        } catch (MessagingException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            return false;
        }
    }
}