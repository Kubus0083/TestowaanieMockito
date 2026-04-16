package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerListyTest {

    private ManagerListy manager;

    @BeforeEach
    void setUp() {
        manager = new ManagerListy();
    }

    @Test
    void testDodajElementPoprawnie() {
        manager.dodajElement("Element 1");
        manager.dodajElement("Element 2");
        manager.dodajElement("Element 3");

        assertEquals(3, manager.pobierzRozmiar());
    }

    @Test
    void testPustaListaNaPoczatku() {
        assertEquals(0, manager.pobierzRozmiar());
    }
}