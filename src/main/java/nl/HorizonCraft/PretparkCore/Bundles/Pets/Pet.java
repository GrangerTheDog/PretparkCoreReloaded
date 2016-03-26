/*
 * Copyright (c) 2015-2016 Tim Medema
 *
 * This plugin has no licence on it. But that DOESN'T mean you can use it.
 * See the COPYRIGHT.txt for in the root for more information.
 *
 * You are allowed to:
 * - Read the code, and use it for educational purposes.
 * - Ask me questions about how this plugin works and what some of the components do.
 *
 * You are NOT allowed to:
 * - Use it without my explicit permission.
 */

package nl.HorizonCraft.PretparkCore.Bundles.Pets;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * This class has been created on 09/23/2015 at 7:37 PM by Cooltimmetje.
 */
public class Pet {

    private UUID entity;
    private Player owner;
    private String name;
    private PetType petType;

    public Pet(Player owner, String name, PetType petType) {
        this.owner = owner;
        this.name = name;
    }

    public void spawn(){
        Entity entity;
    }

    public Entity getEntity() {
        Entity entity;



        return null;
    }

    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }
}
