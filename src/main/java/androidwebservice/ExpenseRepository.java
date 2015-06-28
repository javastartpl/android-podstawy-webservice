package androidwebservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestResource
@RequestMapping("/expenses")
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
}
