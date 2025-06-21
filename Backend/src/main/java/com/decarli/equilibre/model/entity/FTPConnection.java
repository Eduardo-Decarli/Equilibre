package com.decarli.equilibre.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FTPConnection {

    private String connection;
    private String user;
    private String password;

}
