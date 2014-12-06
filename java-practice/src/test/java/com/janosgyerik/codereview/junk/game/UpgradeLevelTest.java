package com.janosgyerik.codereview.junk.game;

import java.util.HashSet;
import java.util.Set;

class Feature {
    private final String name;
    private final float effectPerLevel;
    private final int[] upgradeCosts;

    Feature(String name, float effectPerLevel, int[] upgradeCosts) {
        this.name = name;
        this.effectPerLevel = effectPerLevel;
        this.upgradeCosts = upgradeCosts;
    }

    public int getLevelCap() {
        return upgradeCosts.length;
    }
}

class Gold extends Feature {
    public Gold() {
        super("Gold", .01f, new int[]{
                100, 200, 300, 400, 500,
                600, 700, 800, 900, 1000
        });
    }
}

class Health extends Feature {
    public Health() {
        super("Health", 100, new int[]{
                100, 200, 300, 400, 500,
                600, 700, 800, 900, 1000
        });
    }
}


class PlayerFeature {
    private final Feature feature;
    private int level;

    PlayerFeature(Feature feature) {
        this.feature = feature;
        this.level = 1;
    }

    void upgrade() {
        if (level < feature.getLevelCap()) {
            ++level;
        }
    }
}

class Player {
    Set<PlayerFeature> features = new HashSet<PlayerFeature>();

    public void addFeature(PlayerFeature feature) {
        features.add(feature);
    }
}

class Game {
    public void initPlayers() {
        Gold gold = new Gold();
        Health health = new Health();

        Player player1 = new Player();
        player1.addFeature(new PlayerFeature(gold));
        player1.addFeature(new PlayerFeature(health));

        Player player2 = new Player();
        player2.addFeature(new PlayerFeature(gold));
        player2.addFeature(new PlayerFeature(health));
    }
}

public class UpgradeLevelTest {
}
