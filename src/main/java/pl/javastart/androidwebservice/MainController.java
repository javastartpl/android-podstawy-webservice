package pl.javastart.androidwebservice;

import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@EnableScheduling
public class MainController {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private DataSource dataSource;

	@ResponseBody
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public Iterable<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@ResponseBody
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public Category insertCategory(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	@ResponseBody
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public Category getCategory(@PathVariable Long id) {
		return categoryRepository.findOne(id);
	}


	@Scheduled(cron = "0 0 3 * * *")
	public void removeAllData() throws ScriptException, SQLException {
        categoryRepository.deleteAll();
		expenseRepository.deleteAll();
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("data.sql"));
		System.out.println("All data removed " + new Date());
	}

}
