package com.cycleon.boardgames.web;

import com.cycleon.boardgames.services.models.KalahGame;
import com.cycleon.boardgames.services.service.KalahGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class KalahGameController {

    @Autowired
    private KalahGameService kalahGameService;


    @GetMapping("/kalahgame/{id}")
    @ResponseBody
    public KalahGame getKalahGame(@PathVariable int id){
        return kalahGameService.getKalahGame(id);
    }

    @PostMapping(path="/kalahgame/{id}/start")
    @ResponseBody
    public KalahGame restartKalahGame(@PathVariable int id){
        return kalahGameService.restartKalahGame(id);
    }

    @PostMapping(path="/kalahgame/{id}/sow/{index}")
    @ResponseBody
    public KalahGame sowInKalahGame(@PathVariable int id, @PathVariable int index){
        return kalahGameService.sowAndSimulate(id,index);
    }
}
