package mvcrest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

// Jersey ce 'rest' da prevede u '/rest/*'
@ApplicationPath("rest")
public class RestApp extends ResourceConfig {

    public RestApp() {
         packages("mvcrest.avioni");
    }
}
