package com.janosgyerik.stackoverflow.junk;

import java.util.concurrent.ConcurrentHashMap;

enum SessionObject {

    INSTANCE;

    /** ConcurrentHashMap to avoid external synchronization  **/
    /**
     * Key as callId , value as HashMap to store the key value pairs  *
     */
    private ConcurrentHashMap<String, ConcurrentHashMap<String, String>> session = new ConcurrentHashMap<String, ConcurrentHashMap<String, String>>();

    /**
     * Used to create a new Map when new call ivr is established in the first vxml file
     *
     * @param callId the call id for which to create new session
     */
    public void createSession(String callId) {

        ConcurrentHashMap<String, String> callMap = new ConcurrentHashMap<String, String>();
        session.putIfAbsent(callId, callMap);
    }

    /**
     * Used to get the existing stored values for the particular call Id *
     */
    public ConcurrentHashMap<String, String> getSession(String callId) {

        return session.get(callId);
    }

    /**
     * Used to remove the details of the particular call Id , when call is disconnected *
     */
    public void deleteSession(String callId) {

        session.remove(callId);
    }

}

public class SessionKeysTest {
}
