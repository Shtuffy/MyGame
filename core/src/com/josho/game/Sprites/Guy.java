package com.josho.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.josho.game.TestGame;

public class Guy extends Sprite
{
    public enum State { FALLING, JUMPING, STANDING, MOVING, DEAD, GAME_OVER }
    public State currentState;
    public State previousState;

    public static World world;
    public static Body b2body;
    public static Sprite sprite;

    private boolean guyIsDead;

    public static int lives = 3;

    public Guy(World world)
    {
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;

        defineCharacter();
    }

    public void update(float dt)
    {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void defineCharacter()
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
        fdef.shape = shape;
        b2body.createFixture(fdef);

        //sprite
        sprite = new Sprite(new Texture("bluesquare.png"));
        sprite.setSize(15 / TestGame.PPM, 15 / TestGame.PPM);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        b2body.setUserData(sprite);
    }

    public boolean isDead(boolean dead)
    {
        if(dead)
        {
            guyIsDead = true;
            lives--;
            defineCharacter();
        }
        else
        {
            guyIsDead = false;
        }
        return guyIsDead;
    }

    public State getState()
    {
        if(lives < 0)
        {
            return State.GAME_OVER;
        }
        if(guyIsDead)
        {
            return State.DEAD;
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

    public void dispose()
    {
        sprite.getTexture().dispose();
    }
}
