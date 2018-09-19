package com.welyab.tutorials.restful.api;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("api")
public class TutorialsRestJavaeeApplication extends ResourceConfig {

    public TutorialsRestJavaeeApplication() {
	register(CustomerResource.class);
    }
}
