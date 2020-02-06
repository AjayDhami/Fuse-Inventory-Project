package com.fuse.inventory.api.repository;

import com.fuse.inventory.api.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    /* Search item by Item Name */
    @Query(value = "select * from inventory where name=:name", nativeQuery = true)
    List<Inventory> searchItemsByName(@Param("name") String name);

    /* Search item by Inventory Type */
    @Query(value = "select * from inventory where type=:name", nativeQuery = true)
    List<Inventory> searchItemsByInventoryType(@Param("name") String name);

    /* Search item by Quantity of Item */
    @Query(value = "select v.name from inventory as v group by v.name having count(*)=:quantity", nativeQuery = true)
    List<String> searchItemsByQuantity(@Param("quantity") int quantity);

    /*Display the Name of Items and Frequency of those Items*/
    @Query(value = "select v.name as item,count(*) as quantity from inventory as v group by v.name ", nativeQuery = true)
    List<Object[]> showItemsNameAndQuantityOfItem();

    /*Search Quantity of Item using provided Item Name*/
    @Query(value = "select count(*) from inventory where name=:name", nativeQuery = true)
    int searchQuantityOfParticularItem(@Param("name") String name);

    /*Search Type(Consumable/NonConsumable) of Item using provided Item Name*/
    @Query(value = "select distinct type from inventory where name=:name", nativeQuery = true)
    String searchItemTypeByName(@Param("name") String name);

}
