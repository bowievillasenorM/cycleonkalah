package com.cycleon.boardgames.web;

import com.cycleon.boardgames.services.models.KalahGame;
import com.cycleon.boardgames.services.service.KalahGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class KalahGameController {

  @Autowired
  private KalahGameService kalahGameService;


  @GetMapping("/kalahgame/{id}")
  @ResponseBody
  public KalahGame getKalahGame(@PathVariable int id) {
    return kalahGameService.getKalahGame(id);
  }

  @PostMapping(path = "/kalahgame/{id}/start")
  @ResponseBody
  public KalahGame restartKalahGame(@PathVariable int id,
      @RequestParam(name = "houses", required = false, defaultValue = "6") Integer numberOfHouses,
      @RequestParam(name = "seeds", required = false, defaultValue = "4") Integer numberOfSeeds,
      @RequestParam(name = "isEmptyCapture", required = false, defaultValue = "false") Boolean isEmptyCapture,
      @RequestParam(name = "doesCountRemainingSeed", required = false, defaultValue = "false") Boolean doesCountRemainingSeed
  ) {
    return kalahGameService.initializeKalahGame(id, numberOfHouses, numberOfSeeds, isEmptyCapture,
        doesCountRemainingSeed);
  }

  @PostMapping(path = "/kalahgame/{id}/sow/{index}")
  @ResponseBody
  public KalahGame sowInKalahGame(@PathVariable int id, @PathVariable int index) {
    return kalahGameService.sowAndSimulate(id, index);
  }
}
