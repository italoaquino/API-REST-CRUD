package com.PrimeiroProjeto.Projeto.service;

import com.PrimeiroProjeto.Projeto.entity.CategoryEntity;
import com.PrimeiroProjeto.Projeto.entity.ChecklistItemEntity;
import com.PrimeiroProjeto.Projeto.exception.ResourceNotFoundExecption;
import com.PrimeiroProjeto.Projeto.repository.CategoryRepository;
import com.PrimeiroProjeto.Projeto.repository.ChecklistItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ChecklistItemService {


    private CategoryRepository  categoryRepository;
    private ChecklistItemRepository checklistItemRepository;

    public ChecklistItemService(CategoryRepository categoryRepository, ChecklistItemRepository checklistItemRepository){
        this.checklistItemRepository = checklistItemRepository;
        this.categoryRepository = categoryRepository;
    }

    private void validateChecklistItemDate(String description, Boolean isCompleted, LocalDate deadLine, String categoryGuid){
        if(!StringUtils.hasText(description)){
            throw new IllegalArgumentException("description cannot be null");
        }

        if(!StringUtils.hasText(categoryGuid)){
            throw new IllegalArgumentException("category cannot be null");
        }

        if(isCompleted == null){
            throw new IllegalArgumentException("Checklist item must have a flag indicating if it is completed or not");
        }

        if(deadLine==null){
            throw new IllegalArgumentException("Checklist item must have a deadline");
        }

    }

    public ChecklistItemEntity addNewChecklistItem(String description, Boolean isCompleted, LocalDate deadLine, String categoryGuid) {
        this.validateChecklistItemDate(description, isCompleted, deadLine, categoryGuid);

        CategoryEntity category = this.categoryRepository.findByGuid(categoryGuid)
                .orElseThrow(() -> new ResourceNotFoundExecption("category not found"));

        ChecklistItemEntity checklistItemEntity = new ChecklistItemEntity();
        checklistItemEntity.setGuid(UUID.randomUUID().toString());
        checklistItemEntity.setIsCompleted(isCompleted);
        checklistItemEntity.setDeadLine(deadLine);
        checklistItemEntity.setPostDate(LocalDate.now());
        checklistItemEntity.setDescription(description);
        checklistItemEntity.setCategory(category);

        log.debug("{}", checklistItemEntity);

        return checklistItemRepository.save(checklistItemEntity);

    }

    public Iterable<ChecklistItemEntity> findAllCheckListItem(){
        return this.checklistItemRepository.findAll();
    }

    public void DeleteCheckList(String Guid){

        if(Guid.isEmpty()){
            throw new IllegalArgumentException("Guid connot be null or empty!");
        }
        ChecklistItemEntity checklistItem = checklistItemRepository.findByGuid(Guid).orElseThrow(() -> new ResourceNotFoundExecption("Checklist item not found!"));
        checklistItemRepository.delete(checklistItem);
    }

    public ChecklistItemEntity findByGuid(String Guid){
        if(Guid.isEmpty()){
            throw new IllegalArgumentException("Checklist guid cannot empty!");
        }

        return this.checklistItemRepository.findByGuid(Guid).orElseThrow
                (() -> new ResourceNotFoundExecption("Checklist item not found!"));

    }

    public ChecklistItemEntity UpdateCheckList(String Guid, String description, Boolean isCompleted, LocalDate deadLine, String categoryGuid){

        if(!StringUtils.hasText(Guid)){
            throw new IllegalArgumentException("Checklist item not found!");
        }

        ChecklistItemEntity checklistItem = this.checklistItemRepository.findByGuid(Guid)
                .orElseThrow(() -> new ResourceNotFoundExecption("Checklist item not found"));

        if(StringUtils.hasText(description)){
            checklistItem.setDescription(description);
        }
        if(isCompleted != null){
            checklistItem.setIsCompleted(isCompleted);
        }
        if(deadLine != null){
            checklistItem.setDeadLine(deadLine);
        }

        if(categoryGuid != null){
            CategoryEntity category = categoryRepository.findByGuid(Guid)
                    .orElseThrow(() -> new ResourceNotFoundExecption("Category not found!"));
            checklistItem.setCategory(category);
        }

        return this.checklistItemRepository.save(checklistItem);
    }


}
