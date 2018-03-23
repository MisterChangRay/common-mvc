package com.zr.dao.entity;

public class Permission {
    private Integer id;

    private String name;

    private Byte isdeal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getIsdeal() {
        return isdeal;
    }

    public void setIsdeal(Byte isdeal) {
        this.isdeal = isdeal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", isdeal=").append(isdeal);
        sb.append("]");
        return sb.toString();
    }
}