package mvcrest.avioni;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AvionskaKarta implements Serializable {
    private int id;
    private boolean one_way;
    private String from;
    private String to;
    private Date depart_date;
    private Date return_date;
    private AvionskaKompanija avionskaKompanija;
    int version;

    public AvionskaKarta() {
    }

    public AvionskaKarta(int id, boolean one_way, String from, String to, Date depart_date, Date return_date, AvionskaKompanija avionskaKompanija, int version) {
        this.id = id;
        this.one_way = one_way;
        this.from = from;
        this.to = to;
        this.depart_date = depart_date;
        this.return_date = return_date;
        this.avionskaKompanija = avionskaKompanija;
        this.version = version;
    }

    public AvionskaKarta id(int id){
        this.id = id;
        return this;
    }

    public AvionskaKarta one_way(boolean one_way){
        this.one_way = one_way;
        return this;
    }

    public AvionskaKarta from(String from){
        this.from = from;
        return this;
    }

    public AvionskaKarta to(String to){
        this.to = to;
        return this;
    }

    public AvionskaKarta depart_date(Date depart_date){
        this.depart_date = depart_date;
        return this;
    }

    public AvionskaKarta return_date(Date return_date){
        this.return_date = return_date;
        return this;
    }

    public AvionskaKarta avionskaKompanija(AvionskaKompanija avionskaKompanija){
        this.avionskaKompanija = avionskaKompanija;
        return this;
    }

    public AvionskaKarta version(int version) {
        this.version = version;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOne_way() {
        return one_way;
    }

    public void setOne_way(boolean one_way) {
        this.one_way = one_way;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(Date depart_date) {
        this.depart_date = depart_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public AvionskaKompanija getAvionskaKompanija() {
        return avionskaKompanija;
    }

    public void setAvionskaKompanija(AvionskaKompanija avionskaKompanija) {
        this.avionskaKompanija = avionskaKompanija;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "AvionskaKarta{" +
                "id=" + id +
                ", one_way=" + one_way +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", depart_date=" + sdf.format(depart_date) +
                ", return_date=" + ((return_date == null) ? "null" : sdf.format(return_date)) +
                ", avionskaKompanija=" + avionskaKompanija +
                ", version=" + version +
                '}';
    }
}
