package com.mygdx.diogenesandroid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.diogenesandroid.JuegoDiogenesVersionFail;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = JuegoDiogenesVersionFail.WIDTH;
		config.height = JuegoDiogenesVersionFail.HEIGHT;
		config.vSyncEnabled = true;
		config.foregroundFPS = 60;
		config.resizable = false;
		new LwjglApplication(new JuegoDiogenesVersionFail(), config);
	}
}
