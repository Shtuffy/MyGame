package com.josho.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.josho.game.Sprites.Guy;
import com.josho.game.TestGame;

public class GameOverScreen implements Screen
{
    private Viewport viewport;
    private Stage stage;
    private static Game game;

    public GameOverScreen(Game game)
    {
        this.game = game;
        viewport = new FitViewport(TestGame.V_WIDTH, TestGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((TestGame) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        Label playAgainLabel = new Label("Click to Play Again", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    @Override
    public void show()
    {

    }

    public static void resetGame(boolean bool)
    {
        if(bool)
        {
            Guy.setLives(3);
            new TestGame();
            game.setScreen(new PlayScreen((TestGame) game));
        }
    }

    @Override
    public void render(float delta)
    {
        if(Gdx.input.justTouched())
        {
            resetGame(true);
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }
}
