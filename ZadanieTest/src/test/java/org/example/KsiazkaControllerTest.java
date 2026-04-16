package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KsiazkaControllerTest {

    @Mock
    private KsiazkaRepository mockRepozytorium;

    @Mock
    private KsiazkaView mockWidok;

    @InjectMocks
    private KsiazkaController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDodajKsiazkePoprawna() {
        when(mockWidok.pobierzTytul()).thenReturn("Wiedźmin");
        when(mockWidok.pobierzAutor()).thenReturn("Andrzej Sapkowski");
        when(mockWidok.pobierzRok()).thenReturn("1993");

        controller.dodajKsiazke();

        verify(mockRepozytorium, times(1)).dodaj(any(Ksiazka.class));
        verify(mockWidok).odswiezTabele(anyList());
        verify(mockWidok).wyczyscPola();
        verify(mockWidok).pokazKomunikat("Książka została dodana!");
    }

    @Test
    void testDodajKsiazkePustyTytul() {
        when(mockWidok.pobierzTytul()).thenReturn("");
        when(mockWidok.pobierzAutor()).thenReturn("Andrzej Sapkowski");
        when(mockWidok.pobierzRok()).thenReturn("1993");

        controller.dodajKsiazke();

        verify(mockRepozytorium, never()).dodaj(any(Ksiazka.class));
        verify(mockWidok).pokazKomunikat("Tytuł i autor nie mogą być puste!");
    }

    @Test
    void testDodajKsiazkeBladBazy() {
        when(mockWidok.pobierzTytul()).thenReturn("Testowa książka");
        when(mockWidok.pobierzAutor()).thenReturn("Testowy Autor");
        when(mockWidok.pobierzRok()).thenReturn("2020");

        doThrow(new RuntimeException("Brak połączenia z bazą"))
                .when(mockRepozytorium).dodaj(any(Ksiazka.class));

        controller.dodajKsiazke();

        verify(mockWidok).pokazKomunikat("Wystąpił błąd podczas dodawania książki.");
    }
}