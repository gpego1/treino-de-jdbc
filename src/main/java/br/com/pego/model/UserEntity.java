package br.com.pego.model;


import java.sql.Date;

public class UserEntity {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Date dateOfBirth;

    public UserEntity() {}


    public UserEntity(Integer id, String name, String email, String password,  Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getId() {
        return this.id;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }


}
