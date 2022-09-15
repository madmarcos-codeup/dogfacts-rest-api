package docrob.dogfactsrestapi.repositories;

import docrob.dogfactsrestapi.data.DogFact;
import org.springframework.data.jpa.repository.JpaRepository;

// this is the gateway between DogFact objects and the db
public interface DogFactsRepository extends JpaRepository<DogFact, Long> {
}
