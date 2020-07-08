package br.com.lucas.pokedex.repositories;

import br.com.lucas.pokedex.models.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String> {
}
