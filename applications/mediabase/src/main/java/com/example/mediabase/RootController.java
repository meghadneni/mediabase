package com.example.mediabase;

import com.example.mediabase.movies.MoviesBean;
import com.example.mediabase.movies.MoviesInitialList;

import com.example.mediabase.podcasts.*;
import com.example.mediabase.podcasts.PodcastsInitialList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RootController {
    private MoviesBean moviesBean;
    private PodcastRepository podcastRepository;
    private MoviesInitialList moviesInitialList;
    private PodcastsInitialList podcastsInitialList;

    public RootController(MoviesBean moviesBean, PodcastRepository podcastRepository, MoviesInitialList moviesInitialList, PodcastsInitialList podcastsInitialList) {
        this.moviesBean = moviesBean;
        this.podcastRepository = podcastRepository;
        this.moviesInitialList = moviesInitialList;
        this.podcastsInitialList = podcastsInitialList;
    }

    @GetMapping("/")
    public String rootPath() {
        return "index";
    }

    @GetMapping("/setup")
    public String setupDatabase(Map<String, Object> model) {

        moviesInitialList.asList().forEach(moviesBean::addMovie);
        model.put("movies", moviesBean.getMovies());


        podcastsInitialList.podcastList().forEach(podcastRepository :: save);
        model.put("podcasts", podcastRepository.findAll());

        return "setup";
    }

}
