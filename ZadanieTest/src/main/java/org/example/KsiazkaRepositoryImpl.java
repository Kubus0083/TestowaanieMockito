package org.example;

import java.util.List;

import java.sql.*;
        import java.util.*;

public class KsiazkaRepositoryImpl implements KsiazkaRepository {

    private Connection polacz() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/4tf_biblioteka1", "root", "");
    }

    @Override
    public void dodaj(Ksiazka k) {
        try (Connection conn = polacz();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO books (tytul, autor, rok_wydania) VALUES (?, ?, ?)")) {
            ps.setString(1, k.getTytul());
            ps.setString(2, k.getAutor());
            ps.setInt(3, k.getRokWydania());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void usun(int id) {
        try (Connection conn = polacz();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM books WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aktualizuj(Ksiazka k) {
        try (Connection conn = polacz();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE books SET tytul=?, autor=?, rok_wydania=? WHERE id=?")) {
            ps.setString(1, k.getTytul());
            ps.setString(2, k.getAutor());
            ps.setInt(3, k.getRokWydania());
            ps.setInt(4, k.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ksiazka> pobierzWszystkie() {
        List<Ksiazka> lista = new ArrayList<>();
        try (Connection conn = polacz();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                lista.add(new Ksiazka(
                        rs.getInt("id"),
                        rs.getString("tytul"),
                        rs.getString("autor"),
                        rs.getInt("rok_wydania")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}