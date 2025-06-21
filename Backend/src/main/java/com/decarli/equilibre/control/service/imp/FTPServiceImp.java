package com.decarli.equilibre.control.service.imp;

import com.decarli.equilibre.model.entity.FTPConnection;
import com.decarli.equilibre.control.service.FTPService;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FTPServiceImp implements FTPService {

    public String[] listFiles(FTPConnection connection) throws IOException {
        FTPClient client = createConnection(connection);

        try{
            String[] files;
            if(!connection.getPath().isBlank()) {
                files = client.listNames(connection.getPath());
            } else {
                System.out.println("Passou aqui");
                files = client.listNames();
            }

            return files;
        } finally {
            if(client.isConnected()) {
                client.logout();
                client.disconnect();
            }
        }
    }

    public void sendFile() {
    }

    private FTPClient createConnection(FTPConnection connection) throws IOException {

        FTPClient client = new FTPClient();

        client.connect(connection.getConnection(), connection.getPort());
        
        client.login(connection.getUser(), connection.getPassword());
        client.enterLocalPassiveMode();

        return client;
    }
}
