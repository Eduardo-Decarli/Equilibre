package com.decarli.equilibre.control.service.imp;

import com.decarli.equilibre.model.entity.FTPConnection;
import com.decarli.equilibre.control.service.FTPService;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FTPServiceImp implements FTPService {

    public String[] listFiles(FTPConnection connection) throws IOException {
        FTPClient client = createConnection(connection);
        try{
            String[] files = client.listNames();
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

        client.connect(connection.getIP(), connection.getPort());
        if (connection.getUser() != null && connection.getPassword() != null) {
            client.login(connection.getUser(), connection.getPassword());
        }

        return client;
    }
}
