//package com.janosgyerik.stackoverflow;
//
//import javax.swing.text.html.parser.DTDConstants;
//import java.com.janosgyerik.examples.util.Map;
//import java.com.janosgyerik.examples.util.concurrent.ConcurrentHashMap;
//
//interface GenericDAO<T, U, V> {
//    User findByKey(String key, Boolean active) throws NotFoundException;
//
//    User findByValue(User user, Boolean active) throws NotFoundException;
//
//}
//
//class User {
//
//    private String userName;
//
//    public String getUserName() {
//        return userName;
//    }
//}
//
//class RAMUserDAO implements GenericDAO<User, String, Boolean> {
//    private static final DTDConstants LOG = null;
//    private static Map<String, User> activeUserMap = new ConcurrentHashMap<String, User>();
//    private static Map<String, User> banUserMap = new ConcurrentHashMap<String, User>();
//
//    public RAMUserDAO() {
//
//    }
//
//    /**
//     * @return banned users
//     */
//    public static Map<String, User> getBanList() {
//        return banUserMap;
//    }
//
//    /**
//     * @return active users
//     */
//    public Map<String, User> getMap() {
//        return activeUserMap;
//    }
//
//    private void ensureValidUser(User user) {
//        if (user == null || user.getUserName() == null) {
//            throw new IllegalArgumentException("user or user.getUserName is null");
//        }
//    }
//
//    private void update(User user, Map<String, User> map, String message) {
//        map.put(user.getUserName(), user);
//        LOG.SYSTEM.info("User: " + user.getUserName() + " " + message);
//    }
//
//    public boolean update(User user, Boolean active) throws NotFoundException {
//        ensureValidUser(user);
//
//        if (active) {
//            update(user, activeUserMap, "has been added/updated");
//        } else {
//            update(user, banUserMap, "has been banned");
//        }
//        return true;
//    }
//
//    private void delete(String username, Map<String, User> map, String message) {
//        map.remove(username);
//        LOG.SYSTEM.info("User: " + user + " " + message);
//    }
//
//    public void delete(User user, Boolean active) {
//        if (active) {
//            delete(user.getUserName(), activeUserMap, "has been removed");
//        } else {
//            delete(user.getUserName(), banUserMap, "has been unbanned");
//        }
//    }
//
//    @Override
//    public User findByKey(String username, Boolean active) throws NotFoundException {
//        if (!isUser(username, active))
//            throw new NotFoundException("User findByKey RAMUserDAO");
//
//        if (active) {
//            return activeUserMap.get(username);
//        } else {
//            return banUserMap.get(username);
//        }
//
//    }
//
//    @Override
//    public User findByValue(User user, Boolean active) throws NotFoundException {
//        if (!isUser(user, active))
//            throw new NotFoundException("User findByValue RAMUserDAO");
//
//        if (active) {
//            return activeUserMap.get(user.getUserName());
//        } else {
//            return banUserMap.get(user.getUserName());
//        }
//
//    }
//
//    private boolean isUser(String userName, Boolean active)
//            throws NotFoundException {
//        if (userName == null)
//            throw new NotFoundException("RAMUserDAO isUser string null");
//        if (active) {
//            // LOG.CONSOLE.debug(activeUserMap);
//            // LOG.CONSOLE.debug(activeUserMap.get(userName));
//            if (activeUserMap == null
//                    || activeUserMap.get(userName) == null)
//                throw new NotFoundException("RAMUserDAO isUser");
//        } else {
//            if (banUserMap == null || banUserMap.get(userName) == null)
//                throw new NotFoundException("RAMUserDAO isUser");
//        }
//
//        return true;
//    }
//
//    private boolean isUser(User user, Boolean active) throws NotFoundException {
//        if (user == null || user.getUserName() == null
//                || !(user instanceof User))
//            throw new NotFoundException("RAMUserDAO isUser");
//
//        if (active) {
//            if (activeUserMap == null
//                    || activeUserMap.get(user.getUserName()) == null)
//                throw new NotFoundException("RAMUserDAO isUser");
//        } else {
//            if (banUserMap == null
//                    || banUserMap.get(user.getUserName()) == null)
//                throw new NotFoundException("RAMUserDAO isUser");
//        }
//
//        return true;
//    }
//
//}
//
//class NotFoundException extends Exception {
//    public NotFoundException(String s) {
//
//    }
//
//    public NotFoundException() {
//
//    }
//}
//
//public class RAMUserDAOTest {
//}
