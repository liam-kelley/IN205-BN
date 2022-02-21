package ensta;

import ensta.controller.Game;
import ensta.controller.SuperFastGame;

public class Main {

	public static void main(String args[]) {
        new Game().init().run();
        //new Game().init("ai","Sylvain","ai","Philippe").run();
        //new SuperFastGame().init().run();
        /*int gamesDone = 0;
        int scoreP1 = 0;
        int scoreP2 = 0;
        while(gamesDone<100){
            if (new SuperFastGame().init("ai","Sylvain","ai","Philippe").run()){scoreP1++;}
            else{scoreP2++;}
            gamesDone++;
        }
        System.out.println("Score de Sylvain = " + scoreP1);
        System.out.println("Score de Philippe = " + scoreP2);*/
        
    }

}
