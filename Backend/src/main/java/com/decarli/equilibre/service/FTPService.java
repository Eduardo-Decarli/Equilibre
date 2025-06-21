package com.decarli.equilibre.service;

import com.decarli.equilibre.model.entity.FTPConnection;

import java.io.IOException;
import java.util.List;

public interface FTPService {

    String[] listFiles(FTPConnection connection) throws IOException;
    void sendFile();
}
