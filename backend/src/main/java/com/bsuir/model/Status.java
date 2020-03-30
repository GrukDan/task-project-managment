package com.bsuir.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Status {
    private long idstatus;
    private String status;

    @Id
    @Column(name = "idstatus", nullable = false)
    public long getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(long idstatus) {
        this.idstatus = idstatus;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status1 = (Status) o;
        return idstatus == status1.idstatus &&
                Objects.equals(status, status1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idstatus, status);
    }
}
