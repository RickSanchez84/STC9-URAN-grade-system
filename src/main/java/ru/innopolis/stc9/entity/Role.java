package ru.innopolis.stc9.entity;


import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    private long id;
    @Column
    private String name;

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String nameRole) {
        this.name = name;
    }

}
