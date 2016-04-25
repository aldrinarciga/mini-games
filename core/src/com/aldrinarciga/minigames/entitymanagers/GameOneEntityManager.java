package com.aldrinarciga.minigames.entitymanagers;

import com.aldrinarciga.minigames.entities.Entity;

/**
 * Created by perpetualwave on 25/04/2016.
 */
public class GameOneEntityManager extends EntityManager {

    @Override
    public void checkCollisions() {

    }

    @Override
    public void removeEntity(Entity entity) {

    }

    @Override
    public void dispose() {
        for(Entity entity : entities){
            entity.dispose();
        }
    }

}
