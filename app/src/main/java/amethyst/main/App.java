package amethyst.main;

import amethyst.view.Amethyst;
import amethyst.controller.Controller;
import com.formdev.flatlaf.FlatDarkLaf;

public class App {
    public static void main(String[] args) {
        FlatDarkLaf.setup();

        new Controller(new Amethyst());
    }
}
