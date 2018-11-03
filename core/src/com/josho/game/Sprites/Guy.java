package com.josho.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.josho.game.Screens.PlayScreen;
import com.josho.game.TestGame;

public class Guy extends Sprite
{
    public enum State { FALLING, JUMPING, STANDING, MOVING, DEAD, GAME_OVER, DASHING }
    public static State currentState;
    public static State previousState;

    public static World world;
    public static Body b2body;
    public static Sprite sprite;

    private static boolean guyIsDead;

    public static int startingLives;
    private static int score;

    private static float maxDistance;

    public Guy(World world)
    {
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;

        setLives(99);
        score = 0;
        maxDistance = 0f;

        defineCharacter();
    }

    public void update(float dt)
    {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        currentState = getState();
        previousState = currentState;

        if(b2body.getLinearVelocity().x > 0 && b2body.getPosition().x > maxDistance)
        {
            maxDistance = b2body.getPosition().x;
        }
    }

    public static void defineCharacter()
    {
        //body
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / TestGame.PPM, 32 / TestGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        //fixture
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(6 / TestGame.PPM, 6 / TestGame.PPM);
        fdef.filter.categoryBits = TestGame.GUY_BIT;
        fdef.filter.maskBits = TestGame.DEFAULT_BIT | TestGame.SPIKE_BIT | TestGame.COIN_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        //sprite
        sprite = new Sprite(new Texture("bluesquare.png"));
        sprite.setSize(15 / TestGame.PPM, 15 / TestGame.PPM);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        b2body.setUserData(sprite);

        PolygonShape bodyCollision = new PolygonShape();
        bodyCollision.setAsBox(6 / TestGame.PPM, 6 / TestGame.PPM);
        fdef.shape = bodyCollision;

        b2body.createFixture(fdef).setUserData("bodyCollision");
    }

    public static boolean isDead(boolean dead)
    {
        if(dead)
        {
            guyIsDead = true;
            startingLives--;
            score = 0;
            defineCharacter();
            guyIsDead = false;
        }
        else
        {
            guyIsDead = false;
        }
        return guyIsDead;
    }

    public State getState()
    {
        if(startingLives <= 0)
        {
            return State.GAME_OVER;
        }
        if(guyIsDead)
        {
            return State.DEAD;
        }
        if(b2body.getLinearVelocity().x > 2)
        {
            return State.DASHING;
        }
        if(b2body.getLinearVelocity().y > 0)
        {
            return State.JUMPING;
        }
        if(b2body.getLinearVelocity().y < 0)
        {
            return State.FALLING;
        }
        if(b2body.getLinearVelocity().x != 0)
        {
            return State.MOVING;
        }
        else
        {
            return State.STANDING;
        }
    }

    public static void markForDeletion()
    {
        PlayScreen.activeBodies.remove(b2body);
        PlayScreen.deleteBodies.add(b2body);
    }

    public static void setLives(int lives)
    {
        startingLives = lives;
    }

    public static int getLives()
    {
        return startingLives;
    }

    public static Integer getScore()
    {
        if(b2body.getLinearVelocity().x > 0 && b2body.getPosition().x >= maxDistance)
        {
            score+=10;
        }
        return score;
    }

    public static void dispose()
    {
        sprite.getTexture().dispose();
    }
}