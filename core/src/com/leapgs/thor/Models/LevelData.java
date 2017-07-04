package com.leapgs.thor.Models;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Leap-Pancho on 7/3/2017.
 */

public class LevelData {

    private int levelNumber;
    private float levelTime;
    private int numberOfTargets;

    private Array<Target> targets;

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public float getLevelTime() {
        return levelTime;
    }

    public void setLevelTime(float levelTime) {
        this.levelTime = levelTime;
    }

    public int getNumberOfTargets() {
        return numberOfTargets;
    }

    public Array<Target> getTargets() {
        return targets;
    }

    public void setTargets(Array<Target> targets) {
        this.targets = targets;
    }

    public void setNumberOfTargets(int numberOfTargets) {
        this.numberOfTargets = numberOfTargets;
    }

    public LevelData() {

    }

    @Override
    public String toString() {
        return "LEVEL "+levelNumber+" TIME "+levelTime+" NUMBER TARGETS "+numberOfTargets+" TARGETS "+targets.toString();
    }
}
