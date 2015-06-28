package androidwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        return "Dziala!";
    }

}
