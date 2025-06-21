package com.decarli.equilibre.control.service;

import com.decarli.equilibre.model.entity.FTPConnection;

import java.io.IOException;

public interface FTPService {

    String[] listFiles(FTPConnection connection) throws IOException;
    void sendFile();
}
