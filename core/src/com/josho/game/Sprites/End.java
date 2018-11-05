package com.josho.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.josho.game.Screens.PlayScreen;
import com.josho.game.TestGame;

public class End extends InteractiveTileObject
{
    public End(World world, TiledMap map, Rectangle bounds)
    {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(TestGame.SPIKE_BIT);
    }

    @Override
    public void onHit()
    {
        TestGame.levelTracker = TestGame.levelTracker + 1;
        PlayScreen.nextLevel(TestGame.levelTracker);
    }
}
