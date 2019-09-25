package com.example.mediabase.moviesui;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import com.example.mediabase.moviesui.MovieUI;

public class MovieClient {

    private static ParameterizedTypeReference<List<MovieUI>> movieListType = new ParameterizedTypeReference<List<MovieUI>>() {
    };
    private RestOperations restOperations;
    private String moviesURL;


    public MovieClient(String moviesURL, RestOperations restOperations) {
        this.restOperations = restOperations;
        this.moviesURL = moviesURL;
    }

    public void create(MovieUI movieUI) {
        restOperations.postForEntity(moviesURL, movieUI, MovieUI.class);
    }

    public List<MovieUI> getAll() {
        return restOperations.exchange(moviesURL, HttpMethod.GET, null, movieListType).getBody();
    }

    public void delete(Long id) {
        String deleteURL = new StringBuilder(moviesURL).append("/").append(id).toString();
        restOperations.delete(deleteURL);
    }

    public int count(String field, String key) {
        String URI = UriComponentsBuilder.fromUriString(moviesURL + "/count")
                .queryParam("field", field)
                .queryParam("key", key)
                .build().toUriString();
        return restOperations.getForEntity(URI, Integer.class).getBody();
    }

    public int countAll() {
        return restOperations.getForEntity(moviesURL + "/count", Integer.class).getBody();
    }

    public List<MovieUI> findAll(int offset, int size) {
        String URI = UriComponentsBuilder.fromUriString(moviesURL)
                .queryParam("start", offset)
                .queryParam("pageSize", size)
                .toUriString();
        return restOperations.exchange(URI, HttpMethod.GET, null, movieListType).getBody();
    }

    public List<MovieUI> findRange(String field, String key, int offset, int size) {
        String URI = UriComponentsBuilder.fromUriString(moviesURL)
                .queryParam("field", field)
                .queryParam("key", key)
                .queryParam("start", offset)
                .queryParam("pageSize", size)
                .toUriString();
        return restOperations.exchange(URI, HttpMethod.GET, null, movieListType).getBody();

    }
}