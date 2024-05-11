package Pegas.controller;

import Pegas.entity.Characters;
import Pegas.entity.Result;
import Pegas.service.ServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/images")
public class ControllerAPI {

    private final ServiceApi serviceApi;
    @Value("${app.image.api}")
    private String CHARACTER_API;
    Integer number = 1;
    @GetMapping
    public String getCharacters(Model model)
    {
        Characters allCharacters = serviceApi.getAllCharacters(CHARACTER_API);
        model.addAttribute("characters", allCharacters.getResults());
        return "table";
    }
    @GetMapping("/{id}")
    public String getCharacters(@PathVariable Long id, Model model)
    {
        Result character = serviceApi.getCharacterById(CHARACTER_API+"/"+id);
        model.addAttribute("character", character);
        return "character";
    }

    @PostMapping("page/{direction}")
    public String getCharactersByPage(@PathVariable String direction, Model model)
    {
        if(direction.equals("prev")&&number>0){
            number--;
        }else number++;
        Characters allCharacters = serviceApi.getAllCharacters(CHARACTER_API+"/?page="+number);
        model.addAttribute("characters", allCharacters.getResults());
        return "table";
    }
}
