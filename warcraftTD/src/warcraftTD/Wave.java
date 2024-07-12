package warcraftTD;

import java.util.*;

public class Wave {

    // nombre de monstres à generer
    protected int nbMonstre;

    // position du spawn pour chaque monstre
    protected Position spawn;

    // vie supplementaire à ajouter aux monstres
    protected double difficulty;

    /**
     * initialise la vague en fonction de:
     * @param nbMonstre
     * @param spawn
     * @param difficulty
     */
    public Wave(int nbMonstre, Position spawn, double difficulty) {
        this.nbMonstre = nbMonstre;
        this.difficulty = difficulty;
        this.spawn = new Position(spawn.x, spawn.y);
    }

    /**
     * @return une liste de BaseMonster de la taille de nbMonstre
     */
    public List<Monster> generateBase() {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        for (int i = 0; i < nbMonstre; i++) {
            Monster monster = new BaseMonster(spawn);
            monster.health+=difficulty;
            monsters.add(monster);
        }
        return monsters;
    }

    /**
     * @return une liste de FlyingMonster de la taille de nbMonstre
     */
    public List<Monster> generateFlying() {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        for (int i = 0; i < nbMonstre; i++) {
            Monster monster = new FlyingMonster(spawn);
            monster.health+=difficulty;
            monsters.add(monster);
        }
        return monsters;
    }

    /**
     * @return une liste composée d'un seul Golem
     */
        public List<Monster> generateGolem() {
            ArrayList<Monster> monsters = new ArrayList<Monster>();
                Monster monster = new Golem(spawn);
                monsters.add(monster);
            
            return monsters;
        }

    /**
     * @return une liste de BaseMonster et de FlyingMonster de la taille de nbMonstre
     */
    public List<Monster> generateBaseFlying(){
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        for (int i = 0; i < nbMonstre; i++) {
            Monster monster = null;
            int random = (int) (Math.random() * 2) + 1;
            if (random == 1)
                monster = new BaseMonster(spawn);
            else
                monster = new FlyingMonster(spawn);
                monster.health+=difficulty;
                monsters.add(monster);
        } 
        return monsters; 
    } 
    /**
     * @return une liste de BaseMonster, de FlyingMonster et de Witch de la taille de nbMonstre
     */
    public List<Monster> generateRandom() {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        for (int i = 0; i < nbMonstre; i++) {
            Monster monster = null;
            int random = (int) (Math.random() * 3) + 1;
            if (random == 1)
                monster = new BaseMonster(spawn);
            else if (random == 2)
                monster = new FlyingMonster(spawn);
            else
                monster = new Witch(spawn);
                monster.health+=difficulty;
            monsters.add(monster);
        }
        return monsters;
    }

    /**
     * @return une liste de BaseMonster, de FlyingMonster de Witch et de Golem de la taille de nbMonstre
     */
    public List<Monster> generateRandomWithGolem() {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        for (int i = 0; i < nbMonstre; i++) {
            Monster monster = null;
            int random = (int) (Math.random() * 4) + 1;
            if (random == 1)
                monster = new BaseMonster(spawn);
            else if (random == 2)
                monster = new FlyingMonster(spawn);
            else if(random == 3)
                monster = new Golem(spawn);
            else 
                monster = new Witch(spawn);
                
                monster.health+=difficulty;
            monsters.add(monster);
        }
        return monsters;
    }
}
