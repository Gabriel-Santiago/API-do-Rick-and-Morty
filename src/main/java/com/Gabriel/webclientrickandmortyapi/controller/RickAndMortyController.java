package com.Gabriel.webclientrickandmortyapi.controller;

import com.Gabriel.webclientrickandmortyapi.client.RickAndMortyClient;
import com.Gabriel.webclientrickandmortyapi.response.CharacterResponse;
import com.Gabriel.webclientrickandmortyapi.response.EpisodeResponse;
import com.Gabriel.webclientrickandmortyapi.response.ListOfEpisodeResponse;
import com.Gabriel.webclientrickandmortyapi.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id){
        return rickAndMortyClient.findAndCharacterById(id);
    }

    @GetMapping("/location/{id}")
    public Mono<LocationResponse> getLocationById(@PathVariable String id){
        return rickAndMortyClient.findAndLocationById(id);
    }

    @GetMapping("/episode/{id}")
    public Mono<EpisodeResponse> getEpisodeById(@PathVariable String id){
        return rickAndMortyClient.findAndEpisodeById(id);
    }

    @GetMapping("/episodes")
    public Flux<ListOfEpisodeResponse> listAllEpisodes(){
        return rickAndMortyClient.listAllEpisodes();
    }

}
