package com.mygdx.diogenesandroid;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.DesktopMenuScreen;
import screens.GameOverScreen;
import screens.GameScreen;


public class JuegoDiogenesVersionFail extends Game {

	public static AssetManager manager;
	public SpriteBatch batch;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;



	@Override
	public void create () {

		batch = new SpriteBatch();
		this.setScreen(new DesktopMenuScreen(this));
		manager = new AssetManager();

		manager.load("music.mp3", Music.class);
		manager.finishLoading();
	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {
		manager.dispose();

	}


}
