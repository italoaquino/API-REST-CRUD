package com.PrimeiroProjeto.Projeto.controller;

import com.PrimeiroProjeto.Projeto.dto.CategoryDTO;
import com.PrimeiroProjeto.Projeto.dto.ChecklistItemDTO;
import com.PrimeiroProjeto.Projeto.entity.CategoryEntity;
import com.PrimeiroProjeto.Projeto.service.CategoryService;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostLoad;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> AllCategories(){
        List<CategoryDTO> resp = StreamSupport.stream(this.categoryService.findAll().spliterator(), false)
            .map(categoryEntity -> CategoryDTO.toTDO(categoryEntity)).collect(Collectors.toList());

        return new ResponseEntity<List<CategoryDTO>>(resp, HttpStatus.OK);

    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryEntity newCategory = this.categoryService.addNewCategory(categoryDTO.getName());

        return new ResponseEntity<>(newCategory.getName(), HttpStatus.CREATED);
    }


    @PutMapping(value = "guid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryDTO categoryDTO) throws ValidationException {

        if(!StringUtils.hasText(categoryDTO.getGuid())){
            throw new ValidationException("category cannot be null or empty");
        }

        this.categoryService.updateCategory(categoryDTO.getGuid(), categoryDTO.getName());

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @DeleteMapping(value = "guid")
    public ResponseEntity<Void> deleteCategory(@PathVariable String guid){
        this.categoryService.deleteCategory(guid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
