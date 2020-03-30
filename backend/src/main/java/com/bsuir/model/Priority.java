package com.bsuir.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Priority {
    private long idpriority;
    private String priority;

    @Id
    @Column(name = "idpriority", nullable = false)
    public long getIdpriority() {
        return idpriority;
    }

    public void setIdpriority(long idpriority) {
        this.idpriority = idpriority;
    }

    @Basic
    @Column(name = "priority", nullable = false, length = 45)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priority priority1 = (Priority) o;
        return idpriority == priority1.idpriority &&
                Objects.equals(priority, priority1.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpriority, priority);
    }
}
