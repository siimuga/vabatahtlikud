package com.example.vabatahtlikud.domain.event.category;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryRepository categoryRepository;

    public List<CategoryInfo> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
       return categoryMapper.categoriesToCategoryInfos(categories);
    }
}
