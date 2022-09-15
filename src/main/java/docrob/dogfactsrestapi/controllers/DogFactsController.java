package docrob.dogfactsrestapi.controllers;

import docrob.dogfactsrestapi.data.DogFact;
import docrob.dogfactsrestapi.repositories.DogFactsRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/dogfacts", produces = "application/json")
public class DogFactsController {
    private DogFactsRepository dogFactsRepository;

    @GetMapping("")
    private List<DogFact> fetchAllFacts() {
        List<DogFact> facts = dogFactsRepository.findAll();
        return facts;
    }
}
