package com.josho.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.josho.game.TestGame;



public class Hud implements Disposable
{
    public Stage stage;
    private Viewport viewport;

    private Integer score;

    Label scoreLabel;
    Label levelLabel;

    public Hud(SpriteBatch sb)
    {
        score = 0;

        viewport = new FitViewport(TestGame.V_WIDTH, TestGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(levelLabel).expandX();
        table.row();
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }
}