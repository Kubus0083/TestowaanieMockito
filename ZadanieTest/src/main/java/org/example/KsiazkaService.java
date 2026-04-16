package org.example;


public class KsiazkaService {
    private final KsiazkaRepository repo;

    public KsiazkaService(KsiazkaRepository repo) {
        this.repo = repo;
    }

    public void dodajKsiazke(Ksiazka k) {
        if (k.getTytul() == null || k.getTytul().trim().isEmpty()) {
            throw new IllegalArgumentException("Tytuł jest wymagany");
        }
        if (k.getRokWydania() < 1450 || k.getRokWydania() > 2026) {
            throw new IllegalArgumentException("Nieprawidłowy rok wydania");
        }
        repo.dodaj(k);
    }
}