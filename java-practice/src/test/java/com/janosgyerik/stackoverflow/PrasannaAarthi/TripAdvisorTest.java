package com.janosgyerik.stackoverflow.PrasannaAarthi;

import java.util.ArrayList;
import java.util.List;

class Station {
    public Connection[] adjacencies;
}

class Connection {

    private Station target;
    private Lane laneColor;

    public Station getTarget() {
        return target;
    }

    public Lane getLane() {
        return laneColor;
    }
}

class TripAdvisor {

    public static void printShortestPath(List<Station> path) {
        Lane lane = null;

        // used to identify when the user needs to change lines
        List<Station> changeStation = new ArrayList<>();

        // list of trains the user need to take to reach a station
        List<Lane> trains = new ArrayList<>();

        for (int i = 0; i < path.size() - 1; i++) {
            Station current = path.get(i);
            Station next = path.get(i + 1);
            for (Connection connection : current.adjacencies) {
                if (connection.getTarget().equals(next)) {
                    Lane train = connection.getLane();
                    trains.add(train);
                    if (!connection.getLane().equals(lane) && lane != null) {
                        changeStation.add(current);
                    }
                }
                lane = connection.getLane();
            }
        }

        System.out.println("trains" + "$" + trains);
        if (trains.size() == 1) {
            System.out.println("Take a " + trains.get(0) + "liner from" + path.get(0) + "to reach" + path.get(path.size()));
        } else {
            System.out.println("Take a " + trains.get(0) + "liner from" + path.get(0) + "Get down at" + changeStation.get(0));
        }
    }

    private int test2() {
        return 1;
    }

}

enum Lane {
    RED, BLUE, GREEN, YELLOW, BLACK
}

public class TripAdvisorTest {
}
