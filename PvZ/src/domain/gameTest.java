package domain;

import domain.plants.*;
import domain.zombies.Basic;
import domain.zombies.Zombie;

public class gameTest {
    public static void main(String[] args) {
        //Test01();
        Test02();
        //Test03();
    }
    public static void Test01(){
        Game game = new Game();

        //Colocar un Peashooter en (2, 2)
        Peashooter peashooter = new Peashooter(0, 2, game);
        WallNut wallNut = new WallNut(4,2,game);
        game.addPlant("wallNut",4,2);
        game.addPlant("peashooter", 0, 2);

        // Colocar dos zombies en diferentes posiciones

        game.addZombie("basic",2);
        Basic zombie1 = (Basic) game.getUnit()[10][2];

        // Crear threads para cada zombie
        Thread zombie1Thread = new Thread(zombie1);
        zombie1Thread.start();

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
    public static void Test02(){
        Game game = new Game();
        game.addPlant("sunflower",0,2);
        Sunflower sunflower = (Sunflower) game.getUnit()[0][2];
        Thread sunflowerThread = new Thread(sunflower);
        sunflowerThread.start();

        // Simulación del juego
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
    public static void Test03(){
        Game game = new Game();
        game.addPlant("eciplant",0,2);
        ECIPlant Eciplant = (ECIPlant) game.getUnit()[0][2];
        Thread eciThread = new Thread(Eciplant);
        eciThread.start();

        // Simulación del juego
        while (game.getSuns() != 200) {
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
}
