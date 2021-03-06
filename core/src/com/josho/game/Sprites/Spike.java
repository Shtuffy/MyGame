package com.josho.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.josho.game.TestGame;

public class Spike extends InteractiveTileObject
{
    public Spike(World world, TiledMap map, Rectangle bounds)
    {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(TestGame.SPIKE_BIT);
    }

    @Override
    public void onHit()
    {
        Guy.markForDeletion();
        System.out.println("spike");
    }
}
