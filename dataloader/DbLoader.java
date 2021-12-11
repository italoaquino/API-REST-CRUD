package com.PrimeiroProjeto.Projeto.dataloader;

import com.PrimeiroProjeto.Projeto.entity.CategoryEntity;
import com.PrimeiroProjeto.Projeto.entity.ChecklistItemEntity;
import com.PrimeiroProjeto.Projeto.repository.CategoryRepository;
import com.PrimeiroProjeto.Projeto.service.CategoryService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DbLoader implements CommandLineRunner {


    private CategoryRepository categoryRepository;

    private CategoryService categoryService;


    public DbLoader(CategoryService categoryService, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<String> categoryNames = Arrays.asList(
            "Trabalho", "Saude", "Pessoal", "Outros"
        );

        for(String categoryName: categoryNames){

            List<ChecklistItemEntity> catOpt= this.categoryRepository.findByName(categoryName);

            if(!catOpt.isEmpty()){
                categoryService.addNewCategory(categoryName);

            }
        }



    }
}
