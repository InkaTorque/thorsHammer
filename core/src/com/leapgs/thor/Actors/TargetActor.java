package com.leapgs.thor.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.leapgs.thor.Enums.TargetMovementType;
import com.leapgs.thor.Screens.GameplayScreen;

/**
 * Created by Leap-Pancho on 7/3/2017.
 */

public class TargetActor extends Actor {

    public Rectangle bounds;

    private float movementAmplitude,movementSpeed = 50.0f,direction;
    private GameplayScreen screen;
    private TargetMovementType type;
    private Vector2 spawnPos;

    private Texture targetTexture;

    private HammerActor hammer;

    public TargetActor(GameplayScreen screen , float x , float y , float movementAmplitude , TargetMovementType type, HammerActor hammer) {
        this.screen = screen;
        this.movementAmplitude = movementAmplitude;
        this.type = type;
        targetTexture = new Texture("sprites/target.png");

        this.hammer = hammer;

        setPosition(x, y);
        spawnPos = new Vector2(x,y);
        setSize(targetTexture.getWidth(),targetTexture.getHeight());
        bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());

        direction = 1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(targetTexture,getX(),getY(),getWidth(),getHeight());
    }

    @Override
    public void act(float delta) {
        bounds.set(getX(),getY(),getWidth(),getHeight());
        if(type!=TargetMovementType.STATIC)
        {
            moveTarget(type,delta);
        }
        checkHammerCollision();
    }

    private void checkHammerCollision() {
        if(bounds.overlaps(hammer.bounds))
        {
            screen.targetDestroyed();
            remove();
        }
    }

    private void moveTarget(TargetMovementType type,float delta) {
        switch (type)
        {
            case HORIZONTAL:moveHorizontal(delta);break;
            case VERTICAL:moveVertical(delta);break;
        }
    }

    private void moveVertical(float delta) {
        if(direction == 1 && (getY() >= spawnPos.y + movementAmplitude))
        {
            direction = -1;
        }
        else
        {
            if(direction == -1 && (getY() <= spawnPos.y - movementAmplitude))
            {
                direction = 1;
            }
        }
        setY(getY() + direction*movementSpeed*delta);

    }

    private void moveHorizontal(float delta) {
        if(direction == 1 && (getX() >= spawnPos.y + movementAmplitude))
        {
            direction = -1;
        }
        else
        {
            if(direction == -1 && (getX() <= spawnPos.y - movementAmplitude))
            {
                direction = 1;
            }
        }
        setX(getX() + direction*movementSpeed*delta);
    }
}
