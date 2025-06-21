package com.decarli.equilibre.service.imp;

import com.decarli.equilibre.model.entity.FTPConnection;
import com.decarli.equilibre.service.FTPService;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.util.List;

public class FTPServiceImp implements FTPService {

    public String[] listFiles(FTPConnection connection) throws IOException {
        FTPClient client = createConnection(connection);

        String[] files = client.listNames();
        client.logout();
        client.disconnect();
        return files;
    }

    public void sendFile() {
    }

    private FTPClient createConnection(FTPConnection connection) throws IOException {
        FTPClient client = new FTPClient();

        client.connect(connection.getConnection());
        client.login(connection.getUser(), connection.getPassword());

        return client;
    }
}
