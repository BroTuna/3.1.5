package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import project.entity.User;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "http://94.198.50.185:7081/api/users";
    private HttpHeaders httpHeaders = new HttpHeaders();
    URLConnection connection;

    public List<User> showAllUsers() throws IOException {
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity
                , new ParameterizedTypeReference<List<User>>() {});
        httpHeaders.add("Cookie", String.valueOf(responseEntity.getHeaders().getFirst(httpHeaders.SET_COOKIE)));
        List<User> allUsers =responseEntity.getBody();
        return allUsers;
    }

    public void saveUser (User user) {
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,httpHeaders);
        ResponseEntity<String> responseEntity =  restTemplate.postForEntity(url, userHttpEntity, String.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
    }

    public void updateUser(User user) {
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, userHttpEntity, String.class);
        responseEntity.getHeaders();
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());

    }

    public void delete(Long id) {
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<User> userHttpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, userHttpEntity
        , String.class);
        responseEntity.getHeaders();
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
    }
}
