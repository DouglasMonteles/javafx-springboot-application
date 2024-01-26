package com.doug.jfx.store.services;

import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public interface UploadFileService {

    String uploadFile(File file, String destinationPath);

    List<String> uploadFile(List<File> files, String destinationPath);

}
