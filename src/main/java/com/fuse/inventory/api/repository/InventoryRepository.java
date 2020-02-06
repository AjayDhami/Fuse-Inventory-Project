package com.fuse.inventory.api.repository;

import com.fuse.inventory.api.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query(value = "select * from inventory where name=:name", nativeQuery = true)
    List<Inventory> searchItemsByName(@Param("name") String name);

    @Query(value = "select * from inventory where type=:name", nativeQuery = true)
    List<Inventory> searchItemsByInventoryType(@Param("name") String name);

    /*Display the name of item and frequency of that item in inventory*/
    @Query(value = "select v.name as item,count(*) as quantity from inventory as v group by v.name order by count(*) desc", nativeQuery = true)
    List<Object[]> searchItemsByQuantity();


    @Query(value = "select count(*) from inventory where name=:name", nativeQuery = true)
    int searchQuantityOfParticularItem(@Param("name") String name);
}
