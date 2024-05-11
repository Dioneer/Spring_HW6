package Pegas.service;

import Pegas.entity.Characters;
import Pegas.entity.Result;

public interface ServiceApi {
    Characters getAllCharacters();

    Result getCharacterById(String s);
}
