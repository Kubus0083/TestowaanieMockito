package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class Tabela extends AbstractTableModel {
    private final String[] kolumny = {"ID", "Tytuł", "Autor", "Rok wydania"};
    private List<Ksiazka> ksiazki = new ArrayList<>();

    public void ustawDane(List<Ksiazka> noweKsiazki) {
        this.ksiazki = new ArrayList<>(noweKsiazki);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return ksiazki.size();
    }

    @Override
    public int getColumnCount() {
        return kolumny.length;
    }

    @Override
    public String getColumnName(int kolumna) {
        return kolumny[kolumna];
    }

    @Override
    public Object getValueAt(int wiersz, int kolumna) {
        Ksiazka k = ksiazki.get(wiersz);
        return switch (kolumna) {
            case 0 -> k.getId();
            case 1 -> k.getTytul();
            case 2 -> k.getAutor();
            case 3 -> k.getRokWydania();
            default -> null;
        };
    }
}