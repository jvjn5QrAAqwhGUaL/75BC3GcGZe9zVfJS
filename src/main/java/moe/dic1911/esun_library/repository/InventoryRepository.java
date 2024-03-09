package moe.dic1911.esun_library.repository;

import moe.dic1911.esun_library.data.Inventory;
import moe.dic1911.esun_library.data.UserDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO inventory (inventory_id, isbn) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), ?1);", nativeQuery = true)
    int addBook(String isbn);

    @Query(value = "select * from INVENTORY where isbn = ?1 and status = 0 limit 1", nativeQuery = true)
    Inventory findFirstAvailableByIsbn(String isbn);

    Inventory findFirstByInventoryId(int inventoryId);
}
