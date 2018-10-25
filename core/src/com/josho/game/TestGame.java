package com.josho.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.josho.game.Screens.PlayScreen;

public class TestGame extends Game
{
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	public static SpriteBatch batch;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render ()
	{
		super.render();
	}
}
