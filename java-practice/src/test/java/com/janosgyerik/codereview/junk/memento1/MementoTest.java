package com.janosgyerik.codereview.junk.memento1;

import com.rits.cloning.Cloner;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

enum Criteria {
    ByState,
    ByCode,
    ByParameterKey
}

class Caretaker {

    List<Originator.Memento> snapshots;

    public Caretaker() {
        this.snapshots = new ArrayList<Originator.Memento>();
    }

    public Caretaker(List<Originator.Memento> snapshots) {
        this.snapshots = snapshots;
    }

    public void addSnapshot(Originator.Memento memento) {
        snapshots.add(memento);
    }

    public List<Originator.Memento> getSnapshots() {
        return snapshots;
    }

    public Originator.Memento find(Criteria criteria, Object key) {
        switch (criteria) {
            case ByCode:
                for (Originator.Memento state : snapshots) {
                    if (state.code.equals(key)) {
                        return state;
                    }
                }
                break;
            case ByState:
                for (Originator.Memento snapshot : snapshots) {
                    if (snapshot.state.equals(key)) {
                        return snapshot;
                    }
                }
                break;
            case ByParameterKey:
                for (Originator.Memento snapshot : snapshots) {
                    if (snapshot.parameters.containsKey(key)) {
                        return snapshot;
                    }
                }
        }
        return null;
    }
}

class Originator {

    private String state;
    private Integer code;
    private Map<String, String> parameters = new HashMap<>();

    /**
     * We have only setter here for security purposes (encapsulation). I mean we don't need
     * to provide any access to the secret state for anybody.
     */
    public void setState(String state) {
        this.state = state;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setParameters(Map<String, String> parameters) {
//        this.parameters.clear();
//        this.parameters.putAll(parameters);
        this.parameters = parameters;
    }

    public Memento save() {
        return new Memento(this);
    }

    public void restore(Memento memento) {
        this.state = memento.state;
        this.code = memento.code;
        this.parameters = memento.parameters;
    }

    /**
     * We use Memento class for making a snapshot of Originator state.
     */
    public class Memento {
        public final String state;
        public final Integer code;
        public final Map<String, String> parameters;

        public Memento(Originator o) {
            Cloner cloner = new Cloner();
            this.state = o.state; //cloner.deepClone(o.state);
            this.code = o.code; //cloner.deepClone(o.code);
//            this.parameters = cloner.deepClone(o.parameters);
            this.parameters = o.parameters; //cloner.deepClone(o.parameters);
        }

        /**
         * Here we have only getters. This is a narrow interface which provides
         * access only for clone of state.
         */
//        public String getState() {
//            return state;
//        }
//
//        public Integer getCode() {
//            return code;
//        }
//
//        public Map<String, String> getParameters() {
//            return parameters;
//        }
    }

    @Override
    public String toString() {
        return "Originator{" +
                "state='" + state + '\'' +
                ", code=" + code +
                ", parameters=" + parameters +
                '}';
    }
}

public class MementoTest {
    @Test
    public void test() {
        Originator originator = new Originator();

        originator.setCode(500);
        originator.setState("active");
        originator.setParameters(new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }});

        Caretaker caretaker = new Caretaker();
        caretaker.addSnapshot(originator.save());
        assertEquals(
                "Originator{state='active', code=500, parameters={key1=value1, key2=value2}}",
                originator.toString());

        originator.setCode(1500);
        originator.setState("pending");
        originator.setParameters(new HashMap<String, String>() {{
            put("key1", "value1+");
            put("key2", "value2+");
        }});

        caretaker.addSnapshot(originator.save());
        assertEquals(
                "Originator{state='pending', code=1500, parameters={key1=value1+, key2=value2+}}",
                originator.toString());

        assertEquals(
                "Originator{state='pending', code=1500, parameters={key1=value1+, key2=value2+}}",
                originator.toString());

        Originator.Memento m = caretaker.find(Criteria.ByCode, 500);
        originator.restore(m);
        assertEquals(
                "Originator{state='active', code=500, parameters={key1=value1, key2=value2}}",
                originator.toString());
    }
}
