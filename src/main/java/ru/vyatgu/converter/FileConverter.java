package ru.vyatgu.converter;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.HashMap;


public class FileConverter {

    private final static HashMap<Integer,String> SUPPORTED_FORMATS = new HashMap<>() {
        {
            put(1, "JPG");
            put(2, "JPEG");
            put(3, "PNG");
            put(4, "BMP");
            put(5, "WBMP");
            put(6, "GIF");
        }
    };

    public static void convert(String inputImagePath, String outputImagePath, String formatName) throws WrongMethodException {
        if (!checkFormat(formatName)) throw new WrongMethodException(formatName);
        try(FileInputStream inputStream = new FileInputStream(inputImagePath);
            FileOutputStream outputStream = new FileOutputStream(outputImagePath)) {
            ImageIO.write(ImageIO.read(inputStream), formatName, outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String massConvert(String inputDir, String outputDir, String formatName) {

        if (!checkFormat(formatName)) return "Ошибка неверный формат";
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
                    } else throw new WrongMethodException(managedFile.getFormat());
                } catch (WrongMethodException ex) {
                    errRate += 1;
                }
            }
        }
        return String.format("Удачно конвертированно: %d", sucRate) + String.format(" Возникла ошибка: %d", errRate);
    }

    private static boolean checkFormat(String formatName) {
        return SUPPORTED_FORMATS.containsValue(formatName.toUpperCase());
    }
}
