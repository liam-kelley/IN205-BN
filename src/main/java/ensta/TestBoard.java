package ensta;

import ensta.model.Board;

public class TestBoard {
    public static void main(String args[]) {
        //new Game().init().run();
        Board board = new Board("TestBoard", 10);
        board.print();
    }
}
