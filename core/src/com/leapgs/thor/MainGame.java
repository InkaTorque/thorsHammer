package com.leapgs.thor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.leapgs.thor.Screens.DifficultyScreen;
import com.leapgs.thor.Screens.GameplayScreen;
import com.leapgs.thor.Screens.ResultsScreen;
import com.leapgs.thor.Screens.WelcomeScreen;

public class MainGame extends Game {

	public SpriteBatch batch;
	public Rectangle window;

	public BitmapFont font;

	public Skin skin;
	public Preferences scorePrefs;


	public boolean testing = true;

	@Override
	public void create() {
		batch = new SpriteBatch();

		font = new BitmapFont();

		//Game Window
		window = new Rectangle();
		window.width = 400;
		window.height = 400;
		window.x = 0;
		window.y = 0;
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		scorePrefs = Gdx.app.getPreferences("Score");

		goToWelcomeScreen();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
		skin.dispose();
		this.getScreen().dispose();
	}

	public void goToGameplayLevel(int level)
	{
		setScreen(new GameplayScreen(this,level));
	}

	public void goToWelcomeScreen()
	{
		setScreen(new WelcomeScreen(this));
	}

	public void goToResultsScreen(int previousLevel, boolean won)
	{
		setScreen(new ResultsScreen(this,previousLevel,won));
	}

	public void goToDifficultyScreen()
	{
		setScreen(new DifficultyScreen(this));
	}


}
