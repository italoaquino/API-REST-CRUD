package com.PrimeiroProjeto.Projeto.dto;

import com.PrimeiroProjeto.Projeto.entity.CategoryEntity;
import com.PrimeiroProjeto.Projeto.entity.ChecklistItemEntity;
import com.PrimeiroProjeto.Projeto.service.CategoryService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class CategoryDTO {


    private String guid;

    @NotBlank(message = "Category name cannot be either null or empty!")
    private Long categoryId;


    private String name;


    public static CategoryDTO toTDO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
            .guid(categoryEntity.getGuid())
            .name(categoryEntity.getName())
            .build();

    }
}
