package org.example;

public class Ksiazka {
    private int id;
    private String tytul;
    private String autor;
    private int rokWydania;

    public Ksiazka(int id, String tytul, String autor, int rokWydania) {
        this.id = id;
        this.tytul = tytul;
        this.autor = autor;
        this.rokWydania = rokWydania;
    }

    public int getId() { return id; }
    public String getTytul() { return tytul; }
    public String getAutor() { return autor; }
    public int getRokWydania() { return rokWydania; }

    public void setTytul(String tytul) { this.tytul = tytul; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setRokWydania(int rokWydania) { this.rokWydania = rokWydania; }
}