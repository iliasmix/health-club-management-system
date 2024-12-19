package gui;

import javax.swing.SwingUtilities;

public class MainMenuTest {
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}
