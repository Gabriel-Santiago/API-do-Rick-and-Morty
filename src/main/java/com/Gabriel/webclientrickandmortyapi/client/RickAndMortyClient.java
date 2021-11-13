package com.Gabriel.webclientrickandmortyapi.client;

import com.Gabriel.webclientrickandmortyapi.response.CharacterResponse;
import com.Gabriel.webclientrickandmortyapi.response.EpisodeResponse;
import com.Gabriel.webclientrickandmortyapi.response.ListOfEpisodeResponse;
import com.Gabriel.webclientrickandmortyapi.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class RickAndMortyClient {

    private final WebClient webClient;

    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findAndCharacterById(String id){

        log.info("Buscando o personagem com id [{}]", id);
        return webClient
                .get()
                .uri("/character/"+id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros")))
                .bodyToMono(CharacterResponse.class);

    }

    public Mono<LocationResponse> findAndLocationById(String id){

        log.info("Buscando a localização com id [{}]", id);
        return webClient
                .get()
                .uri("/location/"+id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros")))
                .bodyToMono(LocationResponse.class);

    }

    public Mono<EpisodeResponse> findAndEpisodeById(String id){

        log.info("Buscando o episódio com id [{}]", id);
        return webClient
                .get()
                .uri("/episode/"+id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros")))
                .bodyToMono(EpisodeResponse.class);

    }

    public Flux<ListOfEpisodeResponse> listAllEpisodes(){

        log.info("Listando todos os episódios");
        return webClient
                .get()
                .uri("/episode")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verifique os parametros")))
                .bodyToFlux(ListOfEpisodeResponse.class);
    }

}
