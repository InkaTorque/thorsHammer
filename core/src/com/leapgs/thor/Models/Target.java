package com.leapgs.thor.Models;

import com.leapgs.thor.Enums.TargetMovementType;

/**
 * Created by Leap-Pancho on 7/3/2017.
 */

public class Target {

    private float posX,posY,movementAmplitude;
    private TargetMovementType movementType;

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getMovementAmplitude() {
        return movementAmplitude;
    }

    public void setMovementAmplitude(float movementAmplitude) {
        this.movementAmplitude = movementAmplitude;
    }

    public TargetMovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(TargetMovementType movementType) {
        this.movementType = movementType;
    }

    public Target(float x, float y, float movementAmplitude, TargetMovementType type) {
        posX = x;
        posY = y;
        this.movementAmplitude = movementAmplitude;
        movementType = type;
    }

    public Target() {
    }

    @Override
    public String toString() {
        return "X "+posX+" Y "+posY+" AMPLITUDE "+movementAmplitude+" TYPE "+movementType;
    }
}
