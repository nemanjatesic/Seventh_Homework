package mvcrest.database;

import mvcrest.avioni.AvionskaKarta;
import mvcrest.avioni.AvionskaKompanija;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection c = null;
    private static Database instance = null;

    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/avioni", "root", "root");
            System.out.println("Connected to DB");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public void addCard(AvionskaKarta avionskaKarta) {
        try {
            if (!validateKarta(avionskaKarta)) return;
            String query = "INSERT INTO avionska_karta (id, one_way, place_from, place_to, depart_date, return_date, version, avionska_kompanija_id)"
                    + " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setBoolean(1, avionskaKarta.isOne_way());
            preparedStmt.setString(2, avionskaKarta.getFrom());
            preparedStmt.setString(3, avionskaKarta.getTo());
            preparedStmt.setDate(4, Date.valueOf(avionskaKarta.getDepart_date().toInstant().atZone(ZoneId.of("UTC")).toLocalDate()));
            if (avionskaKarta.getReturn_date() != null)
                preparedStmt.setDate(5, Date.valueOf(avionskaKarta.getReturn_date().toInstant().atZone(ZoneId.of("UTC")).toLocalDate()));
            else
                preparedStmt.setDate(5, null);
            preparedStmt.setInt(6, avionskaKarta.getVersion());
            preparedStmt.setInt(7, getAvionskaKompanijaIDByName(avionskaKarta.getAvionskaKompanija().getName()));
            preparedStmt.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCompany(AvionskaKompanija avionskaKompanija) {
        try {
            String query = "INSERT INTO avionska_kompanija (id, name, version)"
                    + " VALUES (DEFAULT, ?, ?)";
            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, avionskaKompanija.getName());
            preparedStmt.setInt(2, avionskaKompanija.getVersion());
            preparedStmt.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AvionskaKarta> getAllCards() {
        List<AvionskaKarta> avionskaKarte = new ArrayList<>();
        try {
            String sql = "SELECT * FROM avionska_karta;";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                avionskaKarte.add(makeAvionskaKarta(rs));
            }
        }catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return avionskaKarte;
    }

    public List<AvionskaKompanija> getAllCompanies() {
        List<AvionskaKompanija> avionskaKompanije = new ArrayList<>();
        try {
            String sql = "SELECT * FROM avionska_kompanija;";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                avionskaKompanije.add(makeAvionskaKompanija(rs));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return avionskaKompanije;
    }

    public List<AvionskaKarta> filterByOneWay(Boolean oneWay) {
        if (oneWay == null) {
            return getAllCards();
        }
        List<AvionskaKarta> avionskeKarte = new ArrayList<>();
        try {
            String sql = "SELECT * FROM avionska_karta WHERE one_way=" + (oneWay ? 1 : 0) + ";";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                avionskeKarte.add(makeAvionskaKarta(rs));
            }
        }catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return avionskeKarte;
    }

    private AvionskaKompanija getAvionskaKompanijaByID(int id) {
        try {
            String sql = "SELECT * FROM avionska_kompanija WHERE id=" + id + ";";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return makeAvionskaKompanija(rs);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getAvionskaKompanijaIDByName(String name) {
        try {
            String sql = "SELECT * FROM avionska_kompanija WHERE name='" + name + "';";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("id");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private AvionskaKarta getAvionskaKartaByID(int id) {
        try {
            String sql = "SELECT * FROM avionska_karta WHERE id=" + id + ";";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return makeAvionskaKarta(rs);
            }
        }catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private AvionskaKarta makeAvionskaKarta(ResultSet rs) throws SQLException, ParseException {
        AvionskaKarta avionskaKarta = new AvionskaKarta();
        AvionskaKompanija avionskaKompanija = getAvionskaKompanijaByID(rs.getInt("avionska_kompanija_id"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        avionskaKarta
                .id(rs.getInt("id"))
                .one_way(rs.getBoolean("one_way"))
                .from(rs.getString("place_from"))
                .to(rs.getString("place_to"))
                .depart_date(sdf.parse(rs.getString("depart_date")))
                .avionskaKompanija(avionskaKompanija)
                .version(rs.getInt("version"));
        if (rs.getString("return_date") != null) {
            avionskaKarta.return_date(sdf.parse(rs.getString("return_date")));
        }
        return avionskaKarta;
    }

    private AvionskaKompanija makeAvionskaKompanija(ResultSet rs) throws SQLException {
        AvionskaKompanija avionskaKompanija = new AvionskaKompanija();
        avionskaKompanija
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .version(rs.getInt("version"));
        return avionskaKompanija;
    }

    private boolean validateKarta(AvionskaKarta avionskaKarta) {
        if (avionskaKarta.getFrom().equals("")) return false;
        if (avionskaKarta.getTo().equals("")) return false;
        if (avionskaKarta.getAvionskaKompanija() == null) return false;
        if (avionskaKarta.getAvionskaKompanija().getName().equals("")) return false;
        if (avionskaKarta.getDepart_date() == null) return false;
        if (!avionskaKarta.isOne_way()) {
            if (avionskaKarta.getReturn_date() == null) return false;
            if (avionskaKarta.getReturn_date().before(avionskaKarta.getDepart_date())) return false;
        }
        return !avionskaKarta.getFrom().equals(avionskaKarta.getTo());
    }
}
