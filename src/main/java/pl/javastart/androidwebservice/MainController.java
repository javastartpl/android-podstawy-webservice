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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping("/test")
	public String test() {
		return "Dziala! W bazie jest " + expenseRepository.count() + " wydatkow";
	}

	@Scheduled(cron = "0 0 3 * * *")
	public void removeAllData() throws ScriptException, SQLException {
        categoryRepository.deleteAll();
		expenseRepository.deleteAll();
		ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("data.sql"));
		System.out.println("All data removed " + new Date());
	}

}
