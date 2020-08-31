package taf.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import taf.model.Response;
import taf.utils.LocalDateAdapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;


public class RestClientService {

    public final String BASE_URI = "http://localhost:8080/";

    private static Logger log = Logger.getLogger(RestClientService.class);

    private Response response;

    private HttpClient client = HttpClientBuilder.create().build();

    private Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    public Response getResponse() {
        return this.response;
    }

    public void get(String uri) {
        HttpGet request = new HttpGet(BASE_URI + uri);

        request.setHeader(HttpHeaders.ACCEPT, "application/json");

        HttpResponse response = null;
        try {
            log.info("Perform GET request to: " + request.getURI().toString());
            response = client.execute(request); //execute request and write response
        } catch (ClientProtocolException e) {
            log.error("HTTP protocol error!");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Some problems occur or the connection was aborted!");
            e.printStackTrace();
        }

        wrapResponse(response);
    }

    public void post(String uri, Object body) {
        HttpPost request = new HttpPost(BASE_URI + uri);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

        String reqB = gson.toJson(body);

        HttpResponse response = null;
        try {
            StringEntity bodyToPost = new StringEntity(reqB);
            request.setEntity(bodyToPost);

            log.info("Perform POST request to: " + request.getURI().toString());
            response = client.execute(request);
        } catch (UnsupportedEncodingException e) {
            log.error("The Character Encoding is not supported!");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            log.error("HTTP protocol error!");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Some problems occur or the connection was aborted!");
            e.printStackTrace();
        }

        wrapResponse(response);
    }

    public void delete(String uri) {
        HttpDelete request = new HttpDelete(BASE_URI + uri);
        request.setHeader(HttpHeaders.ACCEPT, "application/json");

        HttpResponse response = null;

        try {
            log.info("Perform DELETE request to: " + request.getURI().toString());
            response = client.execute(request);
        } catch (ClientProtocolException e) {
            log.error("HTTP protocol error!");
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Some problems occur or the connection was aborted!");
            e.printStackTrace();
        }

        wrapResponse(response);
    }

    private void wrapResponse(HttpResponse response) {
        if (response == null) {
            Assert.fail("Response is empty!");
        }

        int statusCode = response.getStatusLine().getStatusCode();

        HttpEntity entity = response.getEntity();

        if (entity == null) {
            this.response = new Response(statusCode, "");
            return;
        }

        String body = "";

        try {
            body = EntityUtils.toString(entity, "UTF-8");
        } catch (ParseException e) {
            log.error("Header elements cannot be parsed!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.response = new Response(statusCode, body);
    }
}
