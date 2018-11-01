package com.josho.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.josho.game.TestGame;

public class Coin extends InteractiveTileObject
{
    public Coin(World world, TiledMap map, Rectangle bounds)
    {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(TestGame.COIN_BIT);
    }

    @Override
    public void onHit()
    {
        setCategoryFilter(TestGame.DESTROYED_BIT);
    }
}
