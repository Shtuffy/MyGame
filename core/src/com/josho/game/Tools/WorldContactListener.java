package com.josho.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.josho.game.Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener
{
    @Override
    public void beginContact(Contact contact)
    {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

//        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if(fixA.getUserData() == "bodyCollision" || fixB.getUserData() == "bodyCollision")
        {
            Fixture bodyCollision = fixA.getUserData() == "bodyCollision" ? fixA : fixB;
            Fixture object = bodyCollision == fixA ? fixB : fixA;

            if(object.getUserData() instanceof InteractiveTileObject)
            {
                ((InteractiveTileObject) object.getUserData()).onHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact)
    {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {

    }
}
