package com.decarli.equilibre.service.imp;

import com.decarli.equilibre.model.entity.FTPConnection;
import com.decarli.equilibre.service.FTPService;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FTPServiceImp implements FTPService {

    public String[] listFiles(FTPConnection connection) throws IOException {
        FTPClient client = createConnection(connection);

        try{
            String[] files;
            if(!connection.getPath().isBlank()) {
                files = client.listNames(connection.getPath());
            } else {
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

    public void sendFile(FTPConnection connection) throws IOException {
        FTPClient client = createConnection(connection);
        client.setFileType(FTPClient.BINARY_FILE_TYPE);

        File file = new File(connection.getFilePath());



        try {
            InputStream inputStream = new FileInputStream(connection.getFilePath());
            boolean sucesso = client.storeFile(connection.getPath() + "/" + file.getName(), inputStream);
            if(sucesso) {
                System.out.println("Upload Feito com Sucesso");
            } else {
                System.out.println("Falha ao realizar o Upload");
            }
        }

        finally {
            client.logout();
            client.disconnect();
        }
    }

    private FTPClient createConnection(FTPConnection connection) throws IOException {

        FTPClient client = new FTPClient();

        client.connect(connection.getConnection(), connection.getPort());

        client.login(connection.getUser(), connection.getPassword());
        client.enterLocalPassiveMode();

        return client;
    }
}
