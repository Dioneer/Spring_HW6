package Pegas.service;

import Pegas.entity.Characters;
import Pegas.entity.Result;

public interface ServiceApi {
    Characters getAllCharacters(String s);

    Result getCharacterById(String s);
}
