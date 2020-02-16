package com.mygdx.diogenesandroid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.diogenesandroid.JuegoDiogenes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 600;
		config.height = 400;
		config.vSyncEnabled = true;
		new LwjglApplication(new JuegoDiogenes(), config);
	}
}
