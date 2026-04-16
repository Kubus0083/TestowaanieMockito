package org.example;

import java.util.List;

public class KsiazkaController {
    private final KsiazkaRepository repository;
    private final KsiazkaView view;

    public KsiazkaController(KsiazkaRepository repository, KsiazkaView view) {
        this.repository = repository;
        this.view = view;
    }

    public void uruchom() {
        view.dodajSluchacza(this);
        odswiezTabele();
        view.setVisible(true);
    }

    private void odswiezTabele() {
        List<Ksiazka> ksiazki = repository.pobierzWszystkie();
        view.odswiezTabele(ksiazki);
    }

    public void dodajKsiazke() {
        try {
            String tytul = view.pobierzTytul();
            String autor = view.pobierzAutor();
            String rokTekst = view.pobierzRok();

            if (tytul.isEmpty() || autor.isEmpty()) {
                view.pokazKomunikat("Tytuł i autor nie mogą być puste!");
                return;
            }

            int rok = Integer.parseInt(rokTekst);
            Ksiazka ksiazka = new Ksiazka(0, tytul, autor, rok);
            repository.dodaj(ksiazka);

            odswiezTabele();
            view.wyczyscPola();
            view.pokazKomunikat("Książka została dodana!");

        } catch (NumberFormatException e) {
            view.pokazKomunikat("Rok musi być liczbą!");
        } catch (Exception e) {
            e.printStackTrace();
            view.pokazKomunikat("Wystąpił błąd podczas dodawania książki.");
        }
    }

    public void usunKsiazke() {
        int id = view.pobierzZaznaczonyId();
        if (id == -1) {
            view.pokazKomunikat("Zaznacz książkę do usunięcia!");
            return;
        }
        repository.usun(id);
        odswiezTabele();
    }

    public void aktualizujKsiazke() {
        int id = view.pobierzZaznaczonyId();
        if (id == -1) {
            view.pokazKomunikat("Zaznacz książkę do aktualizacji!");
            return;
        }
        try {
            String tytul = view.pobierzTytul();
            String autor = view.pobierzAutor();
            String rokTekst = view.pobierzRok();

            if (tytul.isEmpty() || autor.isEmpty()) {
                view.pokazKomunikat("Tytuł i autor nie mogą być puste!");
                return;
            }

            int rok = Integer.parseInt(rokTekst);
            Ksiazka ksiazka = new Ksiazka(id, tytul, autor, rok);
            repository.aktualizuj(ksiazka);

            odswiezTabele();
            view.wyczyscPola();
            view.pokazKomunikat("Książka została zaktualizowana!");

        } catch (NumberFormatException e) {
            view.pokazKomunikat("Rok musi być liczbą!");
        } catch (Exception e) {
            e.printStackTrace();
            view.pokazKomunikat("Wystąpił błąd podczas aktualizacji.");
        }
    }
}