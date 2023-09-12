package com.nahaha.platformer.framework;

import com.nahaha.platformer.objects.*;
public enum ObjectId {
       Player(){
        public IGameObject createFrom(float x, float y, float width, float height)
        {
            return new Player(x,y,width,height,this, null);
        }
    }, Platforms(){
        public IGameObject createFrom(float x, float y, float width, float height)
        {
            return new Platforms(x,y,width,height,this);
        }
    }, Portal(){
        public IGameObject createFrom(float x, float y, float width, float height)
        {
            return new Portal(x,y,width,height,this);
        }
    }, Obstacles(){
        public IGameObject createFrom(float x, float y, float width, float height){
            return new Obstacles(x,y,width,height,this);
        }
    },
    ObstaclesTwo(){
        public IGameObject createFrom(float x, float y, float width, float height){
            return new ObstaclesTwo(x,y,width,height,this);
        }
    },
    ObstaclesThree(){
        public IGameObject createFrom(float x, float y, float width, float height){
            return new ObstaclesThree(x,y,width,height,this);
        }
    },
    ObstaclesFour(){
        public IGameObject createFrom(float x, float y, float width, float height){
            return new ObstaclesFour(x,y,width,height,this);

        }
    },
    ObstaclesFive(){
        public IGameObject createFrom(float x, float y, float width, float height){
            return new ObstaclesFive(x,y,width,height,this);
        }
    },
    Enemy(){
        @Override
        public IGameObject createFrom(float x, float y, float width, float height) {
            return new Enemy(x, y, width, height, this, null, 1, null);
        }
    };






    public abstract IGameObject createFrom(float x, float y, float width, float height);
}
