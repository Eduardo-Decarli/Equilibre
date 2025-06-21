package com.decarli.equilibre.control.controller;

import com.decarli.equilibre.model.entity.FTPConnection;
import com.decarli.equilibre.control.service.FTPService;
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

    @GetMapping("findFiles")
    public ResponseEntity<String[]> listFiles(@RequestBody FTPConnection connection) throws IOException {
        String[] files = ftpService.listFiles(connection);
        return ResponseEntity.status(HttpStatus.FOUND).body(files);
    }

    @PostMapping
    public ResponseEntity<Void> sendFile() {
        return null;
    }
}
