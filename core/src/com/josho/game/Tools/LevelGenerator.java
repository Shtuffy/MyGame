package com.josho.game.Tools;

import com.badlogic.gdx.physics.box2d.Body;

public class LevelGenerator
{
    private Body environment;
    private float leftEdge, rightEdge, minGap, maxGap, minWidth, maxWidth, height, y;


    public LevelGenerator(Body environment, float leftEdge, float rightEdge, float minGap, float maxGap, float minWidth, float maxWidth, float height) {
        this.environment = environment;
        this.leftEdge = leftEdge;
        this.rightEdge = rightEdge;
        this.minGap = minGap;
        this.maxGap = maxGap;
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.height = height;
    }

    public void generate(float topEdge)
    {

    }

    public Body getEnvironment() {
        return environment;
    }

    public float getLeftEdge() {
        return leftEdge;
    }

    public float getRightEdge() {
        return rightEdge;
    }

    public float getMinGap() {
        return minGap;
    }

    public float getMaxGap() {
        return maxGap;
    }

    public float getMinWidth() {
        return minWidth;
    }

    public float getMaxWidth() {
        return maxWidth;
    }

    public float getHeight() {
        return height;
    }

    public void setEnvironment(Body environment) {
        this.environment = environment;
    }

    public void setLeftEdge(float leftEdge) {
        this.leftEdge = leftEdge;
    }

    public void setRightEdge(float rightEdge) {
        this.rightEdge = rightEdge;
    }

    public void setMinGap(float minGap) {
        this.minGap = minGap;
    }

    public void setMaxGap(float maxGap) {
        this.maxGap = maxGap;
    }

    public void setMinWidth(float minWidth) {
        this.minWidth = minWidth;
    }

    public void setMaxWidth(float maxWidth) {
        this.maxWidth = maxWidth;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
