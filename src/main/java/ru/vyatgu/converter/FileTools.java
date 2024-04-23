package ru.vyatgu.converter;

import java.io.File;

public class FileTools {
    private final File file;

    public FileTools(File file) {
        this.file = file;
    }

    public String getNameWithoutFormat() {
        return file.getName().substring(0, getDotPosition());
    }

    public String getFormat() {
        return file.getName().substring(getDotPosition() + 1);
    }

    public int getDotPosition() {
        return file.getName().lastIndexOf(".");
    }
}

