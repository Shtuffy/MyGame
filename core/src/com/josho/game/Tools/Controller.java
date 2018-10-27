package com.josho.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.josho.game.TestGame;

public class Controller
{
    Viewport viewport;
    Stage stage;
    OrthographicCamera cam;

    boolean upPressed;
    boolean downPressed;
    boolean leftPressed;
    boolean rightPressed;
    boolean jumpPressed;

    public Controller()
    {
        cam = new OrthographicCamera();
        viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        stage = new Stage(viewport, TestGame.batch);
        Gdx.input.setInputProcessor(stage);

        Table table1 = new Table();
        Table table2 = new Table();
        table1.setFillParent(true);
        table2.setFillParent(true);

        final Image upImg = new Image(new Texture("flatDark25.png"));
        upImg.setSize(Gdx.graphics.getWidth() / 9.5f, Gdx.graphics.getHeight() / 9.5f);
        upImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                upPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                upPressed = false;
            }
        });

        final Image downImg = new Image(new Texture("flatDark26.png"));
        downImg.setSize(Gdx.graphics.getWidth() / 9.5f, Gdx.graphics.getHeight() / 9.5f);
        downImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                downPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                downPressed = false;
            }
        });

        final Image leftImg = new Image(new Texture("flatDark23.png"));
        leftImg.setSize(Gdx.graphics.getWidth() / 9.5f, Gdx.graphics.getHeight() / 9.5f);
        leftImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                leftPressed = false;
            }
        });

        final Image rightImg = new Image(new Texture("flatDark24.png"));
        rightImg.setSize(Gdx.graphics.getWidth() / 9.5f, Gdx.graphics.getHeight() / 9.5f);
        rightImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                rightPressed = false;
            }
        });

        table1.bottom().left();

        table1.add();
        table1.add(upImg).size(upImg.getWidth(), upImg.getHeight());
        table1.add();

        table1.row().pad(5, 5, 5, 5);
        table1.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
        table1.add();
        table1.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());

        table1.row().padBottom(5);
        table1.add();
        table1.add(downImg).size(downImg.getWidth(), downImg.getHeight());
        table1.add();

        table2.bottom().right();

        final Image jumpImg = new Image(new Texture("flatDark27.png"));
        jumpImg.setSize(Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 8);
        jumpImg.addListener(new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                jumpPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button)
            {
                jumpPressed = false;
            }
        });

        table2.add();
        table2.add();
        table2.add();

        table2.row().pad(5, 5, 5, 5);
        table2.add();
        table2.add(jumpImg).size(jumpImg.getWidth(), jumpImg.getHeight());
        table2.add();

        table2.row().padBottom(5);
        table2.add();
        table2.add();
        table2.add();

        stage.addActor(table1);
        stage.addActor(table2);
    }

    public void draw()
    {
        stage.draw();
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }

    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }
}
