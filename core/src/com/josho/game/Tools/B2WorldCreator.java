package com.josho.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.josho.game.Sprites.Coin;
import com.josho.game.Sprites.End;
import com.josho.game.Sprites.Ground;
import com.josho.game.Sprites.Spike;
import com.josho.game.TestGame;

public class B2WorldCreator
{
    public B2WorldCreator(World world, TiledMap map)
    {
        switch (TestGame.levelTracker)
        {
            case 0:
                //create ground bodies/fixtures
                for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
                {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();

                    new Ground(world, map, rect);
                }

                //create spike bodies/fixtures
                for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
                {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();

                    new Spike(world, map, rect);
                }

                //create end bodies/fixtures
                for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
                {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();

                    new End(world, map, rect);
                }
                break;

            case 1:
                //create ground bodies/fixtures
                for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
                {
                    Rectangle rect = ((RectangleMapObject) object).getRectangle();

                    new Ground(world, map, rect);
                }
        }

        //create coin bodies/fixtures
/*        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(world, map, rect);
        } */
    }
}
