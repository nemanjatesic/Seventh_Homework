package mvcrest.avioni;

import mvcrest.database.Database;

import java.util.List;

public class AvioniRepository {

    public static List<AvionskaKompanija> getKompanije(){
        return Database.getInstance().getAllCompanies();
    }

    public static List<AvionskaKarta> getKarte(){
        return Database.getInstance().getAllCards();
    }

    public static List<AvionskaKarta> filterByOneWay(Boolean oneWay) {
        return Database.getInstance().filterByOneWay(oneWay);
    }

    public static AvionskaKarta addKarta(AvionskaKarta avionskaKarta){
        Database.getInstance().addCard(avionskaKarta);
        return avionskaKarta;
    }
}
