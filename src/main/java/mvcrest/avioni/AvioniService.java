package mvcrest.avioni;

import java.util.List;

public class AvioniService {

    public List<AvionskaKompanija> getKompanije() {
        return AvioniRepository.getKompanije();
    }

    public List<AvionskaKarta> getKarte() {
        return AvioniRepository.getKarte();
    }

    public List<AvionskaKarta> filterByOneWay(Boolean oneWay) {
        return AvioniRepository.filterByOneWay(oneWay);
    }

    public AvionskaKarta addKarta(AvionskaKarta avionskaKarta) {
        return AvioniRepository.addKarta(avionskaKarta);
    }
}
