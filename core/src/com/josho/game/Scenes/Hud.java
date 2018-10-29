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
import com.josho.game.Sprites.Guy;
import com.josho.game.TestGame;



public class Hud implements Disposable
{
    public Stage stage;
    private Viewport viewport;

    protected Integer score = 0;
    protected Integer lives = Guy.getLives();

    Label scoreLabel;
    Label levelLabel;
    Label livesLabel;
    Label livesCount;

    public Hud(SpriteBatch sb)
    {
        viewport = new FitViewport(TestGame.V_WIDTH, TestGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        livesCount = new Label(String.format("%02d", lives), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        livesLabel = new Label("Lives", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(levelLabel).expandX();
        table.add(livesLabel).expandX();
        table.row();
        table.add(scoreLabel).expandX();
        table.add(livesCount).expandX();

        stage.addActor(table);
    }

    public void update(float dt)
    {
        lives = Guy.getLives();

        if(lives >= 0)
        {
            livesCount.setText(String.format("%02d", lives));
        }
    }

    @Override
    public void dispose()
    {
        stage.dispose();
    }
}