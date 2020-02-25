package com.mygdx.diogenesandroid;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;


import world.CustomGameMap;
import world.GameMap;
import world.TileType;
import world.TiledGameMap;


public class JuegoDiogenesVersionFail extends Game {


	private GameMap gameMap;
	private OrthographicCamera cam;

	@Override
	public void create () {

		/**
		 * Si queremos el mapa que hemos creado nosotros desde el tmx Creamos un TiledGameMap, si queremos el que hemos creado a partir del codigo
		 * hacemos un new CustomGameMap(), el juego irá por defecto con el mapa ya creado a partir del tmx
		 */
		gameMap = new TiledGameMap();
		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(Gdx.input.isTouched()){
			cam.translate(-Gdx.input.getDeltaX(),Gdx.input.getDeltaY());
			cam.update();
		}

		if(Gdx.input.justTouched()){
			Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0));
			TileType type = gameMap.getTileTypeByLocation(1, pos.x,pos.y);

			if(type != null){
				System.out.println("id of the tile: " + type.getId() + " " + type.getName() + " " + type.getDamage());
			}
		}
		gameMap.render(cam);

	}


	@Override
	public void dispose () {

	}


}
