package com.PrimeiroProjeto.Projeto.controller;

import com.PrimeiroProjeto.Projeto.dto.ChecklistItemDTO;
import com.PrimeiroProjeto.Projeto.entity.ChecklistItemEntity;
import com.PrimeiroProjeto.Projeto.service.ChecklistItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PUT;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/checklist-items")
public class ChecklistController {

    private ChecklistItemService checklistItemService;

    public ChecklistController(ChecklistItemService checklistItemService){
        this.checklistItemService = checklistItemService;
    }

    //get
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChecklistItemDTO>> allChecklistItems(){
        List<ChecklistItemDTO> resp = StreamSupport.stream(this.checklistItemService.findAllCheckListItem().spliterator(), false)
                .map(checklistItemEntity -> ChecklistItemDTO.toDTO(checklistItemEntity))
            .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    //post
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNewCheckListItem(@RequestBody ChecklistItemDTO checklistItemDTO) throws ValidationException {

        if(!StringUtils.hasText(checklistItemDTO.getGuid())){
            throw new ValidationException("Category cannot null");
        }

        ChecklistItemEntity newChecklistItem = this.checklistItemService.addNewChecklistItem(
            checklistItemDTO.getDescription(), checklistItemDTO.getIsCompleted(), checklistItemDTO.getDeadline(), checklistItemDTO.getCategoryGuid()
        );

        return new ResponseEntity<>(newChecklistItem.getGuid(), HttpStatus.CREATED);

    }

    //put update
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCheckListItem(@RequestBody ChecklistItemDTO checklistItemDTO){

        this.checklistItemService.UpdateCheckList(checklistItemDTO.getGuid(),
            checklistItemDTO.getDescription(), checklistItemDTO.getIsCompleted(),
            checklistItemDTO.getDeadline(), checklistItemDTO.getCategoryGuid());

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    //delete
    @DeleteMapping(value = "guid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> DeleteChecklistItem(@PathVariable String guid){
        this.checklistItemService.DeleteCheckList(guid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
