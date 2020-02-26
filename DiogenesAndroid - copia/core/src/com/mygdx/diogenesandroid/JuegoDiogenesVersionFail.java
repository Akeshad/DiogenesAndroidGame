package com.mygdx.diogenesandroid;


import com.badlogic.gdx.Game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import screens.DesktopMenuScreen;
import tools.ScrollingBackground;


public class JuegoDiogenesVersionFail extends Game {

	public static AssetManager manager;
	public SpriteBatch batch;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public ScrollingBackground scrollingBackground;

	private OrthographicCamera cam;
	private StretchViewport viewport;



	@Override
	public void create () {

		batch = new SpriteBatch();
		this.setScreen(new DesktopMenuScreen(this));
		manager = new AssetManager();
		cam = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT, cam);
		viewport.apply();
		cam.position.set(WIDTH / 2, HEIGHT / 2, 0);
		cam.update();
		this.scrollingBackground = new ScrollingBackground();

		manager.load("music.mp3", Music.class);
		manager.finishLoading();
	}

	@Override
	public void render () {
		super.render();
		batch.setProjectionMatrix(cam.combined);//
	}


	@Override
	public void dispose () {
		manager.dispose();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width,height);
		super.resize(width, height);
		scrollingBackground.resize(width,height);
	}


}
