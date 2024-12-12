package domain;

import domain.economy.Brain;
import domain.economy.Sun;
import domain.plants.*;
import domain.zombies.Basic;

public class gameTest {
    public static void main(String[] args) {
        Test01();
        //Test02();
        //Test03();
        //Test04();
        //Test05();
        //Test06();
    }
    public static void Test01(){
        Game game = new Game("pvp");
        try {
            game.addPlant("wallnut",4,2);
            game.addPlant("peashooter", 0, 2);
            game.addZombie("basic",11,2);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }

        Basic zombie1 = (Basic) game.getUnit()[10][2];

        while (zombie1.getLife() > 0) {
            try {
                game.printBoard();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("¡El zombie ha muerto!");
        game.printBoard();
    }
    public static void Test02(){
        Game game = new Game("pvp");
        try {
            game.addPlant("sunflower",0,2);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }

        while (game.getSuns() != 125) {
            try {
                System.out.println(game.getSuns());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("¡Alcanzo la meta de soles!");
        System.out.println(game.getSuns());
    }
    public static void Test03() {
        Game game = new Game("pvp");
        try {
            Sun sun = new Sun(25);
            game.addSuns(sun);
            game.addPlant("eciplant",0,2);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
        while (game.getSuns() != 250) {
            try {
                System.out.println(game.getSuns());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("¡Alcanzo la meta de soles!");
        System.out.println(game.getSuns());
}
    public static void Test04(){
        Game game = new Game("pvp");
        try {
            game.addPlant("potatomine",0,0);
            game.addZombie("basic",11, 0);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
        Basic zombie1 = (Basic) game.getUnit()[10][0];

        // Simulación del juego
        while (zombie1.getLife() > 0) {
            try {
                game.printBoard();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("¡El zombie ha muerto!");
        game.printBoard();
    }
    public static void Test05(){
        Game game = new Game("pvp");
        try {
            Brain brain = new Brain(150);
            game.addBrains(brain);
            game.addPlant("wallnut",0,0);
            game.addZombie("ecizombie",11, 0);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }
        Plant wall = (WallNut) game.getUnit()[0][0];

        while (wall.getLife() > 0) {
            try {
                game.printBoard();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("¡la planta ha muerto!");
        game.printBoard();
    }
    public static void Test06(){
        Game game = new Game("pvp");
        try {
            game.addZombie("brainstein",11, 0);
        } catch (PvZExceptions e) {
            e.printStackTrace();
        }

        // Simulación del juego
        while (game.getBrains() != 125) {
            try {
                System.out.println(game.getBrains());
                game.printBoard();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("¡Alcanzo la meta de cerebros!");
        System.out.println(game.getBrains());
    }
}
