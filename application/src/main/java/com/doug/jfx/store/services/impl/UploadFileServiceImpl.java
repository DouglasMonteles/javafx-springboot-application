package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.services.UploadFileService;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    public static final String BASE_DIRECTORY = System.getProperty("user.dir");
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
    public List<String> uploadFile(List<File> files, String destinationPath) {
        List<String> savedPaths = new ArrayList<>();

        for (File file : files) {
            String path = uploadFile(file, destinationPath);
            savedPaths.add(path);
        }

        return savedPaths;
    }

    @Override
    public String uploadFile(File file, String destinationPath) {
        if (file != null) {
            long instant = System.currentTimeMillis();
            String relativePath = destinationPath + instant + getFileExtension(file.getName());

            File destination = new File(BASE_DIRECTORY + relativePath);

            try {
                Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return relativePath;
            } catch (IOException e) {
                System.out.println("Erro no upload da imagem. Erro: " + e.getMessage());
            }
        }

        return null;
    }

    private String getFileExtension(String fileName) {
        int lastSeparatorIndex = fileName.lastIndexOf('.');
        return fileName.substring(lastSeparatorIndex);
    }
}
