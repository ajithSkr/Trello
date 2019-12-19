package common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ajith.athithyan
 * @project Trello
 */
public class RestClient {
    public static <T> T clientFactory(Class<T> resourceClass, String baseUrl) {
        List<Object> providers = new ArrayList<Object>();
        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        jacksonJsonProvider.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        providers.add(jacksonJsonProvider);
        providers.add(new HttpLogger());
        T resource = JAXRSClientFactory.create(baseUrl, resourceClass, providers);
        WebClient.client(resource).
                header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).
                header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
        return resource;
    }

}
