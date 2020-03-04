package com.mygdx.diogenesandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
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


	/**
	 * Event function activated when the 'Back' Smartphone's button is touched. Exits the game.
	 * @since March 4th, 2020.
	 */
	@Override
	public void onBackPressed() {

		new AlertDialog.Builder(this)
				.setTitle("Exit Game")
				.setMessage("Are you sure you want to close the app?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

					/**
					 * Event function activated when the 'Yes' option from the Dialog is clicked. Exits the game.
					 * @param dialog The dialog that received the click.
					 * @param which The button that was clicked.
					 */
					@Override
					public void onClick(DialogInterface dialog, int which) { finish(); }

				})
				.setNegativeButton("No", null)
				.show();

	}
}
