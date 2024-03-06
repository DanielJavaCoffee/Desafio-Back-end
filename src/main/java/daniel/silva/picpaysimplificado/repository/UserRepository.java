package daniel.silva.picpaysimplificado.repository;

import daniel.silva.picpaysimplificado.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
