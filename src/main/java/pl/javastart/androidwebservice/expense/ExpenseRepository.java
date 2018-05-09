package pl.javastart.androidwebservice.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
	List<Expense> findByName(@Param("name") String name);
}
