package com.josho.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.josho.game.Sprites.End;
import com.josho.game.TestGame;

public class MainMenuScreen implements Screen
{
    protected Stage stage;
    private Viewport viewport;
    private OrthographicCamera cam;
    private SpriteBatch sb;
    private TestGame game;

    public MainMenuScreen(TestGame game)
    {
        this.game = game;
        sb = new TestGame().batch;
        cam = new OrthographicCamera();
        viewport = new FitViewport(TestGame.V_WIDTH, TestGame.V_HEIGHT, cam);

        cam.position.set(viewport.getWorldWidth(), viewport.getWorldHeight(), 0);
        cam.update();

        stage = new Stage(viewport, sb);
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(stage);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.setFillParent(true);
        table.top().pad(50);

        Label titleLabel = new Label("One Squarey BOi", font);
        Label playLabel = new Label("Play", font);
        Label optionsLabel = new Label("Options", font);
        Label exitLabel = new Label("Exit" , font);

        titleLabel.setFontScale(2);

        playLabel.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(game, TestGame.levelTracker));
            }
        });

//        optionsLabel.addListener(new ClickListener()
//        {
//            @Override
//            public void clicked(InputEvent event, float x, float y)
//            {
//                ((Game)Gdx.app.getApplicationListener()).setScreen(new OptionsScreen());
//            }
//        });

        exitLabel.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        });

        table.add(titleLabel);
        table.row();
        table.add(playLabel);
        table.row();
        table.add(optionsLabel);
        table.row();
        table.add(exitLabel);

        stage.addActor(table);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }
}
