package moe.dic1911.esun_library.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.sql.Timestamp;

@Entity
public class BorrowLog {
    @Id
    private Integer logId;
    private Integer userId;
    private Integer inventoryId;
    private Timestamp borrowedAt;
    private Timestamp returnedAt;
    private String note;

    @Transient
    private String name;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer id) {
        this.logId = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer uid) {
        this.userId = uid;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Timestamp getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(Timestamp borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public Timestamp getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Timestamp returnedAt) {
        this.returnedAt = returnedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String comment) {
        this.note = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
