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

	public static AssetManager manager;//helps you load and manage your assets
	public SpriteBatch batch;// is given a texture and coordinates for each rectangle to be drawn
	public static final int WIDTH = 480;//the width of this game
	public static final int HEIGHT = 720;//the height of this game
	public static boolean IS_MOBILE = false;//boolean that represents if this game is played in a mobile device
	private Database database;//The database of the game
	public ScrollingBackground scrollingBackground;//The ScrollingBackground that is going to appear in the game

	public GameCamera cam;//The GameCamera of the game

	/**
	 * The constructor of the class JuegoDiogenesVersionFail
	 * @param database
	 */
	public JuegoDiogenesVersionFail(Database database){
		this.database = database;
	}




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

	@Override
	public void render () {
		super.render();
		batch.setProjectionMatrix(cam.combined());

	}



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
