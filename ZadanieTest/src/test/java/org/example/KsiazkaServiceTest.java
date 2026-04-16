package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KsiazkaServiceTest {

    @Test
    void powinienRzucicWyjatekPrzyPustymTytule() {
        KsiazkaService service = new KsiazkaService(null); // repo niepotrzebne do walidacji

        Ksiazka k = new Ksiazka(0, "", "Jan Kowalski", 2020);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.dodajKsiazke(k);
        });

        assertEquals("Tytuł jest wymagany", ex.getMessage());
    }

    @Test
    void powinienRzucicWyjatekPrzyNiepoprawnymRoku() {
        KsiazkaService service = new KsiazkaService(null);

        Ksiazka k = new Ksiazka(0, "Książka", "Autor", 100);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.dodajKsiazke(k));
        assertTrue(ex.getMessage().contains("Nieprawidłowy rok"));
    }
}