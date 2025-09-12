package gr.uoa.tedi.backend.controller;

import java.util.List;
import gr.uoa.tedi.backend.model.Category;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.uoa.tedi.backend.service.CategoryService;

@RestController
@RequestMapping("/auctions")
@CrossOrigin(origins = "https://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
