package Pegas.service;

import Pegas.entity.Characters;
import Pegas.entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceApiImpl implements ServiceApi{

    private final RestTemplate template;
    private final HttpHeaders headers;
    @Value("${app.image.api}")
    private String CHARACTER_API;
    @Override
    public Characters getAllCharacters() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> responce = template.exchange(CHARACTER_API, HttpMethod.GET,entity, Characters.class);

        return responce.getBody();
    }
    public Result getCharacterById(String CHARACTER_API) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Result> responce = template.exchange(CHARACTER_API, HttpMethod.GET,entity, Result.class);

        return responce.getBody();
    }
}