package com.josho.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.josho.game.TestGame;

public abstract class InteractiveTileObject
{
    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;

    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds)
    {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / TestGame.PPM, (bounds.getY() + bounds.getHeight() / 2) / TestGame.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / TestGame.PPM, bounds.getHeight() / 2 / TestGame.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onHit();

    public void setCategoryFilter(short filterBit)
    {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}
