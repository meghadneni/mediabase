package com.example.mediabase.podcasts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/podcasts")
public class PodcastController {
    private PodcastRepository podcastRepository;

    public PodcastController(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    @GetMapping()
    public Iterable<Podcast> allPodcasts()
    {
        return podcastRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Podcast> create(@RequestBody Podcast podcast)
    {
        podcastRepository.save(podcast);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
