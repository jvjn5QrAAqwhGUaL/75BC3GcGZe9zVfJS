package moe.dic1911.esun_library.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity
public class Inventory {
    @Id
    private Integer inventoryId;

    private String isbn;
    private Integer status;
    private Timestamp addedAt;
    private Timestamp expectReturnAt;

    public Timestamp getExpectReturnAt() {
        return expectReturnAt;
    }

    public void setExpectReturnAt(Timestamp expectReturnAt) {
        this.expectReturnAt = expectReturnAt;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
