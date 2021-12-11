package com.PrimeiroProjeto.Projeto.dto;

import com.PrimeiroProjeto.Projeto.entity.ChecklistItemEntity;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Builder
@Getter
public class ChecklistItemDTO {

    private String guid;

    @NotBlank(message = "Checklist item descpritpion cannot be either null or empty")
    private String description;

    @NotNull(message = "Is completed is mandatory")
    private Boolean isCompleted;

    @NotNull(message = "DeadLine is mandatory")
    private LocalDate deadline;

    private String categoryGuid;


    public static ChecklistItemDTO toDTO(ChecklistItemEntity checklistItemEntity) {
        return ChecklistItemDTO.builder()
            .guid(checklistItemEntity.getGuid())
            .description(checklistItemEntity.getDescription())
            .isCompleted(checklistItemEntity.getIsCompleted())
            .deadline(checklistItemEntity.getDeadLine())
            .categoryGuid(checklistItemEntity.getCategory().getGuid())
            .build();

    }

}
