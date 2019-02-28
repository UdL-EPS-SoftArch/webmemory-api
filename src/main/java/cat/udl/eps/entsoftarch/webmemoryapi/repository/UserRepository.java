package cat.udl.eps.entsoftarch.webmemoryapi.repository;

import cat.udl.eps.entsoftarch.webmemoryapi.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, String> {

  /**
   * Finds the user with given email
   * @param email String email
   * @return Returns user object.
   */
  User findByEmail(String email);
}
