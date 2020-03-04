package com.mygdx.diogenesandroid;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import database.DatabaseGame;
import services.MyService;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new JuegoDiogenesVersionFail(new DatabaseGame(this)), config);
		startService(new Intent(this, MyService.class));

	}
}
