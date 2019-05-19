package cat.udl.eps.entsoftarch.webmemoryapi.repository;

import cat.udl.eps.entsoftarch.webmemoryapi.domain.Game;
import cat.udl.eps.entsoftarch.webmemoryapi.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {
}


