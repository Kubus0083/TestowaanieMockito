package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KsiazkaRepository repo = new KsiazkaRepositoryImpl();   // można później zamienić na mock
            KsiazkaView view = new KsiazkaView();
            KsiazkaController controller = new KsiazkaController(repo, view);
            controller.uruchom();
        });
    }
}