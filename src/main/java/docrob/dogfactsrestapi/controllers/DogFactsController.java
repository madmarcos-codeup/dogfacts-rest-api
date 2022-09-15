package docrob.dogfactsrestapi.controllers;

import docrob.dogfactsrestapi.data.DogFact;
import docrob.dogfactsrestapi.repositories.DogFactsRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{id}")
    private void updateDogFact(@PathVariable Long id, @RequestBody DogFact updatedDogFact) {
        // first check if the record exists
        // if it does not, return a 404
        Optional<DogFact> dogFact = dogFactsRepository.findById(id);
        if(dogFact.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find dog fact id " + id);
        }

        updatedDogFact.setId(id);
        dogFactsRepository.save(updatedDogFact);
    }

    @PostMapping("")
    private void createDogFact(@RequestBody DogFact dogFact) {
        // at this point, our dog fact has been received as the dogFact parameter
        dogFactsRepository.save(dogFact);
    }

    @DeleteMapping("/{id}")
    private void deleteDogFact(@PathVariable Long id) {
        Optional<DogFact> dogFact = dogFactsRepository.findById(id);
        if(dogFact.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find dog fact id " + id);
        }
        dogFactsRepository.deleteById(id);

        // either one of these approaches is fine for throwing a 404 if not found
//        try {
//            dogFactsRepository.deleteById(id);
//        } catch(EmptyResultDataAccessException e) {
//            // send a 404
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find dog fact id " + id);
//        }
    }
}
