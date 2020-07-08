package br.com.lucas.pokedex;

import br.com.lucas.pokedex.models.Pokemon;
import br.com.lucas.pokedex.repositories.PokedexRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, PokedexRepository repository){
		return args -> {
			Flux<Pokemon> pokemonFlux = Flux.just(
					new Pokemon(null, "Bulbasauro", "Semente", "Overgrow", 6.09),
					new Pokemon(null, "Charmander", "Semente", "Overgrow", 6.09),
					new Pokemon(null, "Charizard", "Semente", "Overgrow", 6.09),
					new Pokemon(null, "Snorlax", "Semente", "Overgrow", 6.09))
					.flatMap(repository::save);

			Disposable subscribe = pokemonFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}