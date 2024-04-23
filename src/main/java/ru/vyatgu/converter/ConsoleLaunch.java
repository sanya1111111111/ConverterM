package ru.vyatgu.converter;

import java.util.Scanner;

public class ConsoleLaunch {
    public static void start() {
        while (true) {
            Scanner input = new Scanner(System.in); //Объект отвечающий за ввод данных
            System.out.println("Меню");
            System.out.println("1.Конвертировать файл");
            System.out.println("2.Конвертировать все файлы в директории");
            System.out.println("3. Выход");
            switch (input.nextInt()) {
                case 1:
                    convertFromFile(input);
                    break;
                case 2:
                    convertFromDir(input);
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void convertFromFile(Scanner input) {
        System.out.println("Введите путь к конвертируемому файлу");
        String inputFile = input.next();//Строка отвечающая за путь получает значение из консоли
        System.out.println("Введите путь к итоговому файлу");
        String outputFile = input.next();//Строка отвечающая за  получает значение из консоли
        System.out.println("Введите формат для конвертации файла");
        String format = input.next();//Строка отвечающая за путь получает значение из консоли
        try {
            FileConverter.convert(inputFile, outputFile, format);
        } catch (WrongMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static void convertFromDir(Scanner input) {
        System.out.println("Введите путь к директории с конвертируемыми файлами");
        String inputFile = input.next();
        System.out.println("Введите путь к директории для итоговых файлов");
        String outputFile = input.next();
        System.out.println("Введите формат для конвертации файлов");
        String format = input.next();
        System.out.println(FileConverter.massConvert(inputFile, outputFile, format));

    }
}