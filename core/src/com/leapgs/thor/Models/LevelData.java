package com.leapgs.thor.Models;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Leap-Pancho on 7/3/2017.
 */

public class LevelData {

    private int levelNumber;
    private float levelTime;
    private int numberOfTargets,starTime1,starTime2,starTime3;

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

    public int getStarTime1() {
        return starTime1;
    }

    public void setStarTime1(int starTime1) {
        this.starTime1 = starTime1;
    }

    public int getStarTime2() {
        return starTime2;
    }

    public void setStarTime2(int starTime2) {
        this.starTime2 = starTime2;
    }

    public int getStarTime3() {
        return starTime3;
    }

    public void setStarTime3(int starTime3) {
        this.starTime3 = starTime3;
    }

    @Override
    public String toString() {
        return "LEVEL "+levelNumber+" TIME "+levelTime+" NUMBER TARGETS "+numberOfTargets+" TARGETS "+targets.toString();
    }
}
