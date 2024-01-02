package com.doug.jfx.store.services;

import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public interface UploadFileService {

    void uploadFile(File file, String destinationPath);

    void uploadFile(List<File> files, String destinationPath);

}
