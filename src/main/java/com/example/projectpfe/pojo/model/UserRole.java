package com.example.projectpfe.pojo.model;

import com.example.projectpfe.pojo.emuns.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "roles")
public class UserRole extends Common{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "useRoleId")
    private Long id;


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column
    private boolean checked;

    public UserRole(Long id, RoleEnum role, boolean checked) {
        super();
        this.id = id;
        this.role = role;
        this.checked = checked;
    }


}
