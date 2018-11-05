package com.josho.game.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.*;
import com.josho.game.Scenes.Hud;
import com.josho.game.Sprites.Guy;
import com.josho.game.TestGame;
import com.josho.game.Tools.B2WorldCreator;
import com.josho.game.Tools.Controller;
import com.josho.game.Tools.LevelGenerator;
import com.josho.game.Tools.WorldContactListener;

import java.util.ArrayList;

public class PlayScreen implements Screen
{
    private static TestGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private Guy player;
    private Controller controller;
    private SpriteBatch batch;

    //Screen region variables
    private float screenL;
    private float screenB;
    private float screenT;
    private float screenR;

    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
//    private LevelGenerator gen;
    private Box2DDebugRenderer b2dr;

    //Body arrays
    private Array<Body> tmpBodies;
    public static ArrayList<Body> activeBodies;
    public static Array<Body> deleteBodies;

    //jump varibales
    private float maxJump;
    private float minJump;

    public PlayScreen(TestGame game, int level)
    {
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TestGame.V_WIDTH / TestGame.PPM, TestGame.V_HEIGHT / TestGame.PPM, gamecam);
        hud = new Hud(game.batch);
        batch = new SpriteBatch();

        //screen regions
        screenL = gamePort.getScreenX();
        screenB = gamePort.getScreenY();
        screenT = screenB + gamePort.getScreenHeight();
        screenR = screenL + gamePort.getScreenWidth();

        //jump heights
        maxJump = 4f;
        minJump = 3f;

        //initializing arrays
        tmpBodies = new Array<Body>();
        activeBodies = new ArrayList<Body>();
        deleteBodies = new Array<Body>();

        //map loading
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(TestGame.levels.get(level));
        renderer = new OrthogonalTiledMapRenderer(map, 1 / TestGame.PPM);

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -13), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        player = new Guy(world);
        controller = new Controller();

        world.setContactListener(new WorldContactListener());
    }

    @Override
    public void show()
    {
        activeBodies.add(player.b2body);

        if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            controller.draw();
        }
    }

    public void handleInput(float dt)
    {
        if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            if(player.getState() != Guy.State.DEAD || player.getState() != Guy.State.GAME_OVER)
            {
                if(controller.isRightPressed())
                {
                    player.b2body.setLinearVelocity(new Vector2(1, player.b2body.getLinearVelocity().y));
                }
                else if(controller.isLeftPressed())
                {
                    player.b2body.setLinearVelocity(new Vector2(-1, player.b2body.getLinearVelocity().y));
                }

                if(controller.isUpPressed())
                {
                    //do something
                }
                else if(controller.isDownPressed())
                {
                    //do something
                }

                if((player.getState() != Guy.State.JUMPING) && (player.getState() != Guy.State.FALLING) && (player.previousState == Guy.State.STANDING || player.previousState == Guy.State.MOVING))
                {
                    if(controller.isJumpPressed())
                    {
                        player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
                    }
                }
            }
        }
        else if (Gdx.app.getType() == Application.ApplicationType.Desktop)
        {
            if(player.getState() != Guy.State.DEAD && player.getState() != Guy.State.GAME_OVER)
            {
                if(Gdx.input.isKeyJustPressed(Input.Keys.R))
                {
                    GameOverScreen.resetGame();
                }

                if(Gdx.input.isKeyJustPressed(Input.Keys.X) && ((player.previousState != Guy.State.DASHING) && (player.previousState != Guy.State.FALLING)))
                {
                    player.b2body.applyLinearImpulse(new Vector2(8f, 4f), player.b2body.getWorldCenter(), true);
                }

                if((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) && (player.getState() != Guy.State.JUMPING)
                        && (player.getState() != Guy.State.FALLING) && (player.previousState == Guy.State.STANDING || player.previousState == Guy.State.MOVING))
                {
                    player.b2body.applyLinearImpulse(new Vector2(0, maxJump), player.b2body.getWorldCenter(), true);
                }
                else if((Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) && (player.getState() != Guy.State.JUMPING)
                        && (player.getState() != Guy.State.FALLING) && (player.previousState == Guy.State.STANDING || player.previousState == Guy.State.MOVING))
                {
                    player.b2body.applyLinearImpulse(new Vector2(0, minJump), player.b2body.getWorldCenter(), true);
                }

                if((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) && player.b2body.getLinearVelocity().x <= 2)
                {
                   player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
                }

                if((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) && player.b2body.getLinearVelocity().x >= -2)
                {
                    player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
                }
            }
        }
    }

    public void update(float dt)
    {
        handleInput(dt);

        world.step(1/60f, 6, 2);
        for(int i = 0; i < deleteBodies.size; i++)
        {
            world.destroyBody(player.b2body);
        }
        deleteBodies.clear();

        player.update(dt);
        hud.update(dt);
        gamecam.update();

        renderer.setView(gamecam);

        if(!activeBodies.contains(player.b2body))
        {
            player.isDead(true);
            activeBodies.add(player.b2body);
        }

        if(player.currentState != Guy.State.DEAD)
        {
            gamecam.position.x = player.b2body.getPosition().x;
        }

        if(player.b2body.getPosition().y < screenB)
        {
            player.isDead(true);
            activeBodies.add(player.b2body);
        }

        if(player.getState() == Guy.State.GAME_OVER)
        {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        renderer.render();
        b2dr.render(world, gamecam.combined);

        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        world.getBodies(tmpBodies);
        for(Body body : tmpBodies)
        {
            if(body.getUserData() instanceof Sprite)
            {
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
                sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                sprite.draw(batch);
            }
        }
        batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        gamePort.update(width, height);
        controller.resize(width, height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        Guy.dispose();
    }

    public static void nextLevel(int level)
    {
        game.setScreen(new PlayScreen(game, level));
    }
}