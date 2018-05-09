package pl.javastart.androidwebservice.cleanup;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.javastart.androidwebservice.category.CategoryRepository;
import pl.javastart.androidwebservice.expense.ExpenseRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;

@Service
@EnableScheduling
public class CleanupService {

	private final ExpenseRepository expenseRepository;
	private final CategoryRepository categoryRepository;
	private final DataSource dataSource;

	public CleanupService(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, @Qualifier("dataSource") DataSource dataSource) {
		this.expenseRepository = expenseRepository;
		this.categoryRepository = categoryRepository;
		this.dataSource = dataSource;
	}

	@Scheduled(cron = "0 3 * * * *")
	public void removeAllData() throws ScriptException, SQLException {
        categoryRepository.deleteAll();
		expenseRepository.deleteAll();
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("data.sql"));
		System.out.println("All data removed " + new Date());
	}

}
