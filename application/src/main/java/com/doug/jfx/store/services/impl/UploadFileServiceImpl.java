package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.services.UploadFileService;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private static final String BASE_DIRECTORY = System.getProperty("user.dir");
    private static final String[] PERMITTED_EXTENSIONS = {
            "*.jpg", "*.jpeg", "*.png"
    };

    public static List<File> openFileChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecione uma ou mais imagens...");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", PERMITTED_EXTENSIONS)
        );

        return fileChooser.showOpenMultipleDialog(stage);
    }

    @Override
    public void uploadFile(List<File> files, String destinationPath) {
        for (File file : files) {
            uploadFile(file, destinationPath);
        }
    }

    @Override
    public void uploadFile(File file, String destinationPath) {
        long instant = System.currentTimeMillis();

        File destination = new File(BASE_DIRECTORY + destinationPath + instant + getFileExtension(file.getName()));

        try {
            Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Erro no upload da imagem. Erro: " + e.getMessage());
        }
    }

    private String getFileExtension(String fileName) {
        int lastSeparatorIndex = fileName.lastIndexOf('.');
        return fileName.substring(lastSeparatorIndex);
    }
}
