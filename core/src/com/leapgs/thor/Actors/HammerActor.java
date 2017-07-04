package com.leapgs.thor.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.leapgs.thor.Screens.GameplayScreen;

/**
 * Created by Leap-Pancho on 7/3/2017.
 */

public class HammerActor extends Actor {

    public Rectangle bounds;
    Texture hammerTexture;
    GameplayScreen screen;
    Vector2 spawnPosition,lauchDirection,returnDirection;
    boolean canMove,homing,launchAvailable;
    float speed = 15f,snapThreshold = 10f;

    public HammerActor(GameplayScreen screen , float x , float y) {
        this.screen = screen;
        hammerTexture = new Texture("sprites/hammer.png");
        setSize(hammerTexture.getWidth(),hammerTexture.getHeight());
        setPosition(x - getWidth()/2,y - getHeight()/2);
        bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
        spawnPosition = new Vector2(x - getWidth()/2,y-getHeight()/2);
        canMove=false;
        launchAvailable =true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(hammerTexture,getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(canMove)
        {
            if(homing)
            {
                setPosition(getX()+lauchDirection.x*speed,getY()+lauchDirection.y*speed);
                checkScreenBounds();
            }
            else
            {
                goBackToStartPosition();
            }
        }
        bounds.set(getX(),getY(),getWidth(),getHeight());


    }

    private void goBackToStartPosition() {
        setPosition(getX()+returnDirection.x*speed,getY()+returnDirection.y*speed);
        if(Math.abs(getX()-spawnPosition.x)<=snapThreshold)
        {
            setPosition(spawnPosition.x,spawnPosition.y);
            launchAvailable=true;
            canMove=false;
            if(screen.targetDestroyed)
            {
                screen.spawnNewTarget();
            }
        }
    }

    private void checkScreenBounds() {
        if(getX()>=400 || getY()>=400 || getY()<=0 || getY()<=0)
        {
            calculateReturnDirection();
        }
    }

    public void launch(float currentAngle) {

        if(launchAvailable)
        {
            float XComp,YComp;
            XComp = (float) Math.cos(Math.toRadians(currentAngle));
            YComp = (float) Math.sin(Math.toRadians(currentAngle));
            lauchDirection = new Vector2(XComp,YComp);
            canMove =true;
            homing=true;
            launchAvailable=false;
        }

    }

    public void goBack() {
        calculateReturnDirection();
    }

    private void calculateReturnDirection() {
        returnDirection = new Vector2();
        returnDirection.x = spawnPosition.x - getX();
        returnDirection.y = spawnPosition.y - getY();
        returnDirection = returnDirection.nor();
        homing=false;
        canMove=true;
    }
}
