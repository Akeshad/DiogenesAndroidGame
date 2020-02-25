package com.mygdx.diogenesandroid;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.DesktopMenuScreen;
import screens.GameScreen;


public class JuegoDiogenesVersionFail extends Game {


	public SpriteBatch batch;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;



	@Override
	public void create () {

		batch = new SpriteBatch();
		this.setScreen(new DesktopMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {

	}


}
