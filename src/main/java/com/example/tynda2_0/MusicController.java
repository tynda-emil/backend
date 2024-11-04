package com.example.tynda2_0;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStream;
    @Controller
    public class MusicController {

        @GetMapping("/")
        public ResponseEntity<InputStreamResource> streamAudio() {
            String audioPath = "audio/espresso.mp3"; // Убедитесь, что файл находится в resources/audio/
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(audioPath);

            if (inputStream == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            InputStreamResource resource = new InputStreamResource(inputStream);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=espresso.mp3")
                    .contentType(MediaType.valueOf("audio/mpeg")) // Указываем тип контента
                    .body(resource);
        }
    }

