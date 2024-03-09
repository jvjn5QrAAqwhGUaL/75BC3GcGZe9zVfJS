package moe.dic1911.esun_library.repository;

import moe.dic1911.esun_library.data.BorrowLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BorrowLogRepository extends CrudRepository<BorrowLog, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO BORROW_LOG (LOG_ID, INVENTORY_ID, USER_ID) VALUES ((SELECT NEXT VALUE FOR PUBLIC.BOOK_SEQ), ?1, ?2);", nativeQuery = true)
    int addLog(int inventoryId, int userId);


    BorrowLog findFirstByInventoryId(int inventoryId);

    @Query(value = "select * from BORROW_LOG where USER_ID = ?1 and RETURNED_AT IS NULL;", nativeQuery = true)
    Iterable<BorrowLog> findAllByBorrowedByAndUnreturned(int userId);
}
