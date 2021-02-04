package pl.krystian.imageuploader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.krystian.imageuploader.Model.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {
}
