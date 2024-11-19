package domain;

public abstract class Zombie implements Unit {
    private String name;
    public Zombie(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
