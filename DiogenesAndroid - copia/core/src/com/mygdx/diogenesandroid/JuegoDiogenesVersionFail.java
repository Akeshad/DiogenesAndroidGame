package com.mygdx.diogenesandroid;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import database.Database;
import screens.DesktopMenuScreen;
import tools.GameCamera;
import tools.ScrollingBackground;


public class JuegoDiogenesVersionFail extends Game {

	public static AssetManager manager;//
	public SpriteBatch batch;//
	public static final int WIDTH = 480;//
	public static final int HEIGHT = 720;//
	public static boolean IS_MOBILE = false;//
	private Database database;
	public ScrollingBackground scrollingBackground;//

	public GameCamera cam;//


	public JuegoDiogenesVersionFail(Database database){
		this.database = database;
	}




	/**
	 *
	 */
	@Override
	public void create () {

		batch = new SpriteBatch();

		manager = new AssetManager();
		cam = new GameCamera(WIDTH, HEIGHT);

		if (Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS)
			IS_MOBILE = true;
		IS_MOBILE = true;

		this.scrollingBackground = new ScrollingBackground();

		manager.load("music.mp3", Music.class);
		manager.finishLoading();
		this.setScreen(new DesktopMenuScreen(this, database));
	}

	/**
	 *
	 */
	@Override
	public void render () {
		super.render();
		batch.setProjectionMatrix(cam.combined());

	}


	/**
	 *
	 */
	@Override
	public void dispose () {
		manager.dispose();

	}

	/**
	 *
	 * @param width
	 * @param height
	 */
	@Override
	public void resize(int width, int height) {
		cam.update(width, height);
		super.resize(width, height);
		scrollingBackground.resize(width,height);
	}


}
