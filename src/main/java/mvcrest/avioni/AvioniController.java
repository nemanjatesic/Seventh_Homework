package mvcrest.avioni;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/avioni")
public class AvioniController {

    private final AvioniService avioniService;

    public AvioniController() {
        this.avioniService = new AvioniService();
    }

    @GET
    @Path("/kompanije")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AvionskaKompanija> getKompanije() {
        return avioniService.getKompanije();
    }

    @GET
    @Path("/karte")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AvionskaKarta> getKarte() {
        return avioniService.getKarte();
    }

    @GET
    @Path("/filterKarte/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AvionskaKarta> filterByOneWay(@PathParam("filter") Boolean filter) {
        return avioniService.filterByOneWay(filter);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AvionskaKarta addKarta(AvionskaKarta avionskaKarta) {
        System.out.println(avionskaKarta);
        return avioniService.addKarta(avionskaKarta);
    }
}
