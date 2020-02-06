package com.fuse.inventory.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Unique Id of the Item")
    private int id;

    @Column(name = "name")
    @ApiModelProperty(notes = "Name of the Item")
    private String name;

    @Column(name = "type")
    @ApiModelProperty(notes = "Type of the Item")
    private String type;

    @Column(name = "uid")
    @ApiModelProperty(notes = "Id of the User who added the Item")
    private int uid;

    @Column(name = "addedby")
    @ApiModelProperty(notes = "Brief about User and Item added")
    private String addedby;

    @ManyToMany
    @JoinTable(name = "user_inventory",
            joinColumns = @JoinColumn(name = "inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ApiModelProperty(notes = "User adding the inventory")
    private List<User> users = new ArrayList<User>();
}
