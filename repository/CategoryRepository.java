package com.PrimeiroProjeto.Projeto.repository;

import com.PrimeiroProjeto.Projeto.entity.CategoryEntity;
import com.PrimeiroProjeto.Projeto.entity.ChecklistItemEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.List;

public interface CategoryRepository {

    Optional<CategoryEntity> findByGuid(String guid);

    List<ChecklistItemEntity> findCategoryByGuid(String guid);


    List<ChecklistItemEntity> findByName(String name);
}
