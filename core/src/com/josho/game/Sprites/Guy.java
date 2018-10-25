package com.josho.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.josho.game.TestGame;

public class Guy extends Sprite
{
    public enum State { FALLING, JUMPING, STANDING, MOVING, DEAD, GAME_OVER }
    public State currentState;
    public State previousState;

    public World world;
    public Body b2body;

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
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / TestGame.PPM, 32 / TestGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / TestGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
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
}
