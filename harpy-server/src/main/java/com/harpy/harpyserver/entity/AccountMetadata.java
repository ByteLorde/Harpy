package com.harpy.harpyserver.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="account_metadata")
public class AccountMetadata {

    @Id
    public String id;

    @Column(name="username")
    public String username;

    @Column(name="password")
    public String password;

    @Column(name="pin")
    public int pin;

    @Column(name="proxy")
    public String proxy;

}
