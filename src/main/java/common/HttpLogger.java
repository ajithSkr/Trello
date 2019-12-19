package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ajith.athithyan
 * @project Trello
 */
@Slf4j
public class HttpLogger implements ClientRequestFilter, ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        String reqHeaders = gson.toJson(toMapConverter(requestContext.getHeaders()));

        String path = requestContext.getUri().toString();

        String method = requestContext.getMethod();
        log.info("<--------------------------------");
        log.info("Request: {} -> \n{} ", method, path);
        log.info("RequestHeader: \n{} ", reqHeaders);
        Object entity = requestContext.getEntity();

        if (entity instanceof String) {
            log.info("RequestBody: \n" + entity.toString());
        } else if (entity != null) {
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(entity);
            log.info("RequestBody: \n{}", data);
        }
        log.info("<--------------------------------");
    }

    private static Map<String, String> toMapConverter(final MultivaluedMap<String, Object> map) {
        return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        log.info("-------------------------------->");
        String resHeaders = gson.toJson(responseContext.getHeaders());
        log.info("ResponseHeader: \n{}", resHeaders);
        if (Objects.nonNull(responseContext.getEntityStream())) {
            String response = getBody(responseContext);
            log.info("ResponseBody: \n{}", response);
        }
        log.info("-------------------------------->");
    }

    private String getBody(final ClientResponseContext responseContext) throws IOException {
        final InputStream stream = responseContext.getEntityStream();
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            final byte[] buffer = new byte[1024];
            int length = stream.read(buffer);

            while (length != -1) {
                result.write(buffer, 0, length);
                length = stream.read(buffer);
            }
            responseContext.setEntityStream(new ByteArrayInputStream(result.toByteArray()));
            return result.toString(StandardCharsets.UTF_8.toString());
        }
    }
}
