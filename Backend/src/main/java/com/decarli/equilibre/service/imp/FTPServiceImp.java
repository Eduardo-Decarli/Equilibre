package com.decarli.equilibre.service.imp;

import com.decarli.equilibre.model.entity.FTPConnection;
import com.decarli.equilibre.service.FTPService;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.*;

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
        long totalSize = file.length(); // Tamanho total do arquivo

        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = client.storeFileStream(connection.getPath() + "/" + file.getName())) {

            if (outputStream == null) {
                System.out.println("Falha ao iniciar upload (stream nula).");
                return;
            }

            byte[] buffer = new byte[4096];
            long totalSent = 0;
            int bytesRead;
            int lastPercent = 0;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalSent += bytesRead;

                int percent = (int) ((totalSent * 100) / totalSize);
                if (percent != lastPercent) {
                    System.out.println("Progresso: " + percent + "%");
                    lastPercent = percent;
                }
            }

            outputStream.flush();

            boolean completed = client.completePendingCommand();
            if (completed) {
                System.out.println("Upload concluído com sucesso!");
            } else {
                System.out.println("Falha ao concluir o upload.");
            }

        } finally {
            if (client.isConnected()) {
                client.logout();
                client.disconnect();
            }
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
