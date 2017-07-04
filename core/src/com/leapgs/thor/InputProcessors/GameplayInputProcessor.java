package com.leapgs.thor.InputProcessors;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.leapgs.thor.Screens.GameplayScreen;
import com.leapgs.thor.Screens.WelcomeScreen;

/**
 * Created by Leap-Pancho on 6/22/2017.
 */

public class GameplayInputProcessor extends InputAdapter {

    GameplayScreen screen;

    public GameplayInputProcessor(GameplayScreen screen)
    {
        this.screen=screen;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screen.startAngleCount();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screen.launchHammer();
        return true;
    }
}
