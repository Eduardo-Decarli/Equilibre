package com.decarli.equilibre.controller;

import com.decarli.equilibre.model.entity.FTPConnection;
import com.decarli.equilibre.service.FTPService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/transferFTP")
public class FTPController {

    private FTPService ftpService;

    @GetMapping("")
    public ResponseEntity<String[]> listFiles(@RequestBody FTPConnection connection) throws IOException {
        String[] files = ftpService.listFiles(connection);
        return ResponseEntity.status(HttpStatus.FOUND).body(files);
    }

    @PostMapping
    public ResponseEntity<Void> sendFileByIP() {
        return null;
    }
}
