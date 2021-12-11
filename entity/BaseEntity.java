package com.PrimeiroProjeto.Projeto.entity;


import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseEntity {

    private String guid;
}
