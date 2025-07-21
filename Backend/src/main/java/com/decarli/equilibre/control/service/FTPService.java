package com.decarli.equilibre.control.service;

import com.decarli.equilibre.model.entity.FTPConnection;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;

public interface FTPService {

    String[] listFiles(FTPConnection connection) throws IOException;
    void sendFile(FTPConnection connection) throws IOException;
}
