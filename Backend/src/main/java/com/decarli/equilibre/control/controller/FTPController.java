package com.decarli.equilibre.control.controller;

import com.decarli.equilibre.model.entity.FTPConnection;
import com.decarli.equilibre.control.service.FTPService;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/transferFTP")
public class FTPController {

    @Autowired
    private FTPService ftpService;

    @PostMapping("/findFiles")
    public ResponseEntity<String[]> listFiles(@RequestBody FTPConnection connection) throws IOException {
        String[] files = ftpService.listFiles(connection);
        return ResponseEntity.status(HttpStatus.FOUND).body(files);
    }

    @PostMapping("/sendFile")
    public ResponseEntity<Void> sendFile(@RequestBody FTPConnection connection) throws IOException {
        ftpService.sendFile(connection);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
