package com.bsuir.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Role {
    private long idrole;
    private String role;

    @Id
    @Column(name = "idrole", nullable = false)
    public long getIdrole() {
        return idrole;
    }

    public void setIdrole(long idrole) {
        this.idrole = idrole;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return idrole == role1.idrole &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idrole, role);
    }
}
