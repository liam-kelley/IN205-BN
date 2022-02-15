package ensta;

import ensta.model.Board;

public class TestBoard {
    public static void main(String args[]) {
        //new Game().init().run();
        Board board = new Board("TestBoard", 36);
        board.print();
    }
}
