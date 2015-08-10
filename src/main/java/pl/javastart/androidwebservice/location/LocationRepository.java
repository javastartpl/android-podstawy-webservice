package pl.javastart.androidwebservice.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.androidwebservice.Category;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
}
