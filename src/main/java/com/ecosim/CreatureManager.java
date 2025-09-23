package com.ecosim;

import java.util.ArrayList;

public class CreatureManager {
    Map map;    
    ArrayList<Creature> creatures = new ArrayList<>();
    public CreatureManager(Map map){
        this.map = map;
    }
    public void AddCreature(Creature c){
        creatures.add(c);
    }
    public ArrayList<Creature> getCreatures(){
        return creatures;
    }
    public void RemoveCreature(Creature c){
        creatures.remove(c);
    }
}
