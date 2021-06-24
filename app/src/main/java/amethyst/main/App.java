package amethyst.main;

import amethyst.view.Amethyst;
import amethyst.controller.Controller;

public class App {
    public static void main(String[] args) {
        new Controller(new Amethyst());
    }
}
