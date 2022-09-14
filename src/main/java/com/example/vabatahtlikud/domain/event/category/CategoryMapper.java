package com.example.vabatahtlikud.domain.event.category;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {

    Category categoryInfoToCategory(CategoryInfo categoryInfo);

    CategoryInfo categoryToCategoryInfo(Category category);

    List<CategoryInfo> categoriesToCategoryInfos(List<Category> categories);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category updateCategoryFromCategoryInfo(CategoryInfo categoryInfo, @MappingTarget Category category);
}
