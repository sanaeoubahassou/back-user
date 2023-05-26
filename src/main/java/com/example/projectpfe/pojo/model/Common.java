package com.example.projectpfe.pojo.model;
import javax.persistence.*;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class Common implements Serializable {

    @Column
    private boolean deleted;

}
