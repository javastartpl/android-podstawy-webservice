package androidwebservice;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "expenses", path = "expenses")
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
	
	List<Expense> findByName(@Param("name") String name);
}
