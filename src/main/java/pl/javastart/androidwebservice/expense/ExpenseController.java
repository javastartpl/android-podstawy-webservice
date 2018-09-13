package pl.javastart.androidwebservice.expense;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javastart.androidwebservice.category.Category;
import pl.javastart.androidwebservice.category.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/expenses")
@RestController
public class ExpenseController {

    private ExpenseRepository expenseRepository;
    private CategoryRepository categoryRepository;

    public ExpenseController(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public List<ExpenseDto> getAll() {
        return expenseRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @PostMapping("")
    public ExpenseDto insertExpense(@RequestBody ExpenseDto expenseDto) {
        Expense expense = toEntity(expenseDto);
        expense = expenseRepository.save(expense);
        return toDto(expense);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .map(expense -> ResponseEntity.ok(toDto(expense)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expenseDto) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            expense.setPrice(expenseDto.getPrice());
            expense.setName(expenseDto.getName());
            setCategoryOrThrow(expenseDto, expense);
        } else {
            return ResponseEntity.notFound().build();
        }
        return expenseRepository.findById(id)
                .map(expense -> ResponseEntity.ok(toDto(expense)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
    }

    private Expense toEntity(ExpenseDto expenseDto) {
        ModelMapper modelMapper = new ModelMapper();
        Expense expense = modelMapper.map(expenseDto, Expense.class);
        setCategoryOrThrow(expenseDto, expense);
        return expense;
    }

    private void setCategoryOrThrow(ExpenseDto expenseDto, Expense expense) {
        if(expenseDto.getCategory() != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(expenseDto.getCategory());
            if(categoryOptional.isPresent()) {
                expense.setCategory(categoryOptional.get());
            } else {
                throw new IllegalArgumentException("Category with id " + expenseDto.getCategory() + " not found");
            }
        }
    }

    private ExpenseDto toDto(Expense expense) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Expense, ExpenseDto> typeMap = modelMapper.createTypeMap(Expense.class, ExpenseDto.class);
        typeMap.addMappings(m -> m.skip(ExpenseDto::setCategory));
        ExpenseDto expenseDto = typeMap.map(expense);
        if (expense.getCategory() != null) {
            expenseDto.setCategory(expense.getCategory().getId());
        }
        return expenseDto;
    }

}
