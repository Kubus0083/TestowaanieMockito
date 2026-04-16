package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class KsiazkaView extends JFrame {
    private final Tabela modelTabeli = new Tabela();
    private final JTable tabela;

    private final JTextField poleTytul = new JTextField(15);
    private final JTextField poleAutor = new JTextField(15);
    private final JTextField poleRok = new JTextField(8);

    private final JButton btnDodaj = new JButton("Dodaj książkę");
    private final JButton btnUsun = new JButton("Usuń zaznaczoną");
    private final JButton btnAktualizuj = new JButton("Aktualizuj zaznaczoną");

    public KsiazkaView() {
        super("Moja Biblioteka - MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);

        tabela = new JTable(modelTabeli);

        JPanel panelGora = new JPanel(new GridLayout(2, 3, 10, 10));
        panelGora.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelGora.add(new JLabel("Tytuł:"));
        panelGora.add(new JLabel("Autor:"));
        panelGora.add(new JLabel("Rok wydania:"));
        panelGora.add(poleTytul);
        panelGora.add(poleAutor);
        panelGora.add(poleRok);

        JPanel panelDol = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelDol.add(btnDodaj);
        panelDol.add(btnUsun);
        panelDol.add(btnAktualizuj);

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(panelGora, BorderLayout.NORTH);
        add(panelDol, BorderLayout.SOUTH);
    }

    public void dodajSluchacza(KsiazkaController controller) {
        btnDodaj.addActionListener(e -> controller.dodajKsiazke());
        btnUsun.addActionListener(e -> controller.usunKsiazke());
        btnAktualizuj.addActionListener(e -> controller.aktualizujKsiazke());
    }

    public void odswiezTabele(List<Ksiazka> lista) {
        modelTabeli.ustawDane(lista);
    }

    public String pobierzTytul() { return poleTytul.getText().trim(); }
    public String pobierzAutor() { return poleAutor.getText().trim(); }
    public String pobierzRok() { return poleRok.getText().trim(); }

    public int pobierzZaznaczonyId() {
        int wiersz = tabela.getSelectedRow();
        if (wiersz == -1) return -1;
        return (int) modelTabeli.getValueAt(wiersz, 0);
    }

    public void wyczyscPola() {
        poleTytul.setText("");
        poleAutor.setText("");
        poleRok.setText("");
    }

    public void pokazKomunikat(String wiadomosc) {
        JOptionPane.showMessageDialog(this, wiadomosc);
    }
}