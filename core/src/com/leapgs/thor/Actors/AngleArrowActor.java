package com.leapgs.thor.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Leap-Pancho on 7/4/2017.
 */

public class AngleArrowActor extends Actor {

    Texture arrowTexture;
    private float currentAngle,maxAngle=90f ,angleSpeed = 125f , angleDirection;

    public AngleArrowActor(float x , float y) {
        arrowTexture = new Texture("sprites/angleArrow.png");
        setSize(arrowTexture.getWidth(),arrowTexture.getHeight());
        setPosition(x - getWidth()/2,y - getHeight()/2);
        setOrigin(0,getHeight()/2);
        currentAngle = 0;
        angleDirection=1f;
        setVisible(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(arrowTexture,getX(),getY(),0,getHeight()/2,getWidth(),getHeight(),1.0f,1.0f,getRotation(),0,0,66,25,false,false);
    }

    @Override
    public void act(float delta) {
        changeAngle(delta);
    }

    private void changeAngle(float delta) {

        if(angleDirection == 1)
        {
            if(currentAngle>=maxAngle)
            {
                angleDirection=-1;
            }
        }
        else
        {
            if(angleDirection==-1)
            {
                if(currentAngle<=-maxAngle)
                {
                    angleDirection = 1;
                }
            }
        }
        currentAngle = currentAngle + angleDirection*delta*angleSpeed;
        setRotation(currentAngle);

    }

    public float getCurrentAngle()
    {
        return  currentAngle;
    }

    public void starRotating() {
        setVisible(true);
        currentAngle = 0f;
        angleDirection=1;
    }


}
