package org.example;

import java.util.List;

public interface KsiazkaRepository {
    void dodaj(Ksiazka ksiazka);
    void usun(int id);
    void aktualizuj(Ksiazka ksiazka);
    List<Ksiazka> pobierzWszystkie();
}
