package mvcrest;

import mvcrest.database.Database;
import mvcrest.avioni.AvionskaKarta;
import mvcrest.avioni.AvionskaKompanija;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Tmp {

    public Tmp() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Database.getInstance().addCard(new AvionskaKarta()
                .one_way(true)
                .from("Serbia")
                .to("Italy")
                .depart_date(sdf.parse("2018-09-10"))
                .return_date(null)
                .version(0)
                .avionskaKompanija(new AvionskaKompanija().name("Air Serbia")));
        /*
        Database.getInstance().addCompany(new AvionskaKompanija()
                .name("Emirates")
                .version(0));
        */
        Database.getInstance().getAllCards().forEach(System.out::println);
        Database.getInstance().getAllCompanies().forEach(System.out::println);
    }

    public static void main(String[] args) throws ParseException {
        new Tmp();
    }
}
