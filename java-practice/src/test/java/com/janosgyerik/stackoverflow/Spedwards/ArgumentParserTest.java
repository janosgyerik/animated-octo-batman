package com.janosgyerik.stackoverflow.Spedwards;

import com.sun.media.jfxmedia.MediaManager;

class Config {
    public static void addPlayer(Player p, String r, String l, Player player) {

    }
}

class Player {
    class Server {
        public Player getPlayer(String s) {
            return null;
        }
    }

    public Server getServer() {
        return null;
    }
}

public class ArgumentParserTest {
    private Player player;
    void parseArgs(String playerParam, String rParam, String lParam) {
        Player p = player.getServer().getPlayer(playerParam.split(":")[1]);
        String r = rParam.split(":")[1];
        String l = lParam.split(":")[1];
        Config.addPlayer(p, r, l, player);
    }
    boolean parse(String[] args, Config config) {
        Player p = null;
        String r = null;
        String l = null;
        for (int i = 1; i <= 3; ++i) {
            String arg = args[i];
            String[] parts = arg.split(":");
            String key = parts[0];
            String value = parts[1];
            if (key.equals("u")) {
                p = player.getServer().getPlayer(value);
            } else if (key.equals("r")) {
                r = value;
            } else if (key.equals("l")) {
                l = value;
            }
        }
        if (p != null && r != null && l != null) {
            Config.addPlayer(p, r, l, player);
            return true;
        }
        return false;
    }
}
