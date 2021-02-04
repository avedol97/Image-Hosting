package pl.krystian.imageuploader.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krystian.imageuploader.Model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
