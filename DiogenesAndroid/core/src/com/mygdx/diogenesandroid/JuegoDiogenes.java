package com.mygdx.diogenesandroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import screens.GameScreen;

public class JuegoDiogenes extends Game {

	private GameScreen screen;
	private AssetManager assetManager;
	@Override
	public void create () {

		assetManager = new AssetManager();
		assetManager.load("graphics_packed/tiles/tilepack.atlas", TextureAtlas.class);
		assetManager.finishLoading();
		screen = new GameScreen(this);
		this.setScreen(screen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();

	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	@Override
	public void dispose () {

	}


}
