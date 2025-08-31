package gr.uoa.tedi.backend.service;

import java.util.List;
import gr.uoa.tedi.backend.model.Category;

import org.springframework.stereotype.Service;

import gr.uoa.tedi.backend.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
