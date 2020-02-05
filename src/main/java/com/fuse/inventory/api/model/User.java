package com.fuse.inventory.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Unique Id of the User")
    private int id;

    @Column(name = "name")
    @ApiModelProperty(notes = "Name of the User")
    private String name;

    @Column(name = "email")
    @ApiModelProperty(notes = "Email of the User")
    private String email;

    @ManyToMany(mappedBy = "users")
    @ApiModelProperty(notes = "Inventories added by the User")
    private List<Inventory> inventories =new ArrayList<Inventory>();
}
