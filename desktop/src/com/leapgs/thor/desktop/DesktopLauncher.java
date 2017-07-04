package com.leapgs.thor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.leapgs.thor.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Thor's Mighty Hammer";
		config.width = 400;
		config.height = 400;
		new LwjglApplication(new MainGame(), config);
	}
}
