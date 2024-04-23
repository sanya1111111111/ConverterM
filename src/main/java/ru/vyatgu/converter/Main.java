package ru.vyatgu.converter;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner input = new Scanner(System.in); //Объект отвечающий за ввод данных
            System.out.println("Меню");
            System.out.println("1.Конвертировать файл");
            System.out.println("2.Конвертировать все файлы в директории");
            System.out.println("3. Выход");
            String inputFile;
            String outputFile;
            String format;
            switch (input.nextInt()) {
                case 1:
                    System.out.println("Введите путь к конвертируемому файлу");
                    inputFile = input.next();//Строка отвечающая за путь получает значение из консоли
                    System.out.println("Введите путь к итоговому файлу");
                    outputFile = input.next();//Строка отвечающая за  получает значение из консоли
                    System.out.println("Введите формат для конвертации файла");
                    format = input.next();//Строка отвечающая за путь получает значение из консоли
                    try {
                        FileConverter.convert(inputFile, outputFile, format);
                    } catch (IOException ex) {
                        System.out.println("Ошибка при конверсии файла.");
                    }
                    break;
                case 2:
                    System.out.println("Введите путь к директории с конвертируемыми файлами");
                    inputFile = input.next();
                    System.out.println("Введите путь к директории для итоговых файлов");
                    outputFile = input.next();
                    System.out.println("Введите формат для конвертации файлов");
                    format = input.next();
                    System.out.println(FileConverter.massConvert(inputFile, outputFile, format));
                    break;
                case 3:
                    return;
            }


        }
    }
}
