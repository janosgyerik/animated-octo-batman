package com.janosgyerik.codingame.tron;

class LongStraightEveryStep extends CrazyLongStraight {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        return lastMove = getLongestStraightMove();
    }
}
