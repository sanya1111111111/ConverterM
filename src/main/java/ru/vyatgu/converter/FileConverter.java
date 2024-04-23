package ru.vyatgu.converter;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.HashSet;


public class FileConverter {

    private final static HashSet<String> SUPPORTED_FORMATS = new HashSet<>() {
        {
            add("JPG");
            add("JPEG");
            add("PNG");
            add("BMP");
            add("WBMP");
            add("GIF");
        }
    };

    public static void convert(String inputImagePath, String outputImagePath, String formatName) throws WrongFormatException {
        if (!checkFormat(formatName)) throw new WrongFormatException(formatName);
        try (FileInputStream inputStream = new FileInputStream(inputImagePath);
             FileOutputStream outputStream = new FileOutputStream(outputImagePath)) {
            ImageIO.write(ImageIO.read(inputStream), formatName, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String massConvert(String inputDir, String outputDir, String formatName) throws WrongFormatException {

        if (!checkFormat(formatName)) throw new WrongFormatException(formatName);
        File dir = new File(inputDir);
        int sucRate = 0;
        int errRate = 0;

        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                FileTools managedFile = new FileTools(file);
                try {
                    if (checkFormat(managedFile.getFormat())) {
                        FileConverter.convert(file.getAbsolutePath(), outputDir + "\\" + managedFile.getNameWithoutFormat(), formatName);
                        sucRate += 1;
                    } else errRate += 1;
                } catch (WrongFormatException ex) {
                    System.out.println(ex.getMessage());
                    errRate += 1;
                }
            }
        }
        return String.format("Удачно конвертированно: %d", sucRate) + String.format(" Возникла ошибка: %d", errRate);
    }

    private static boolean checkFormat(String formatName) {
        return SUPPORTED_FORMATS.contains(formatName.toUpperCase());
    }
}
