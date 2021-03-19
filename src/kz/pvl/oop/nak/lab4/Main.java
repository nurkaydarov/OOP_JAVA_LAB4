package kz.pvl.oop.nak.lab4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        final int row = 4;
        final int col = 5;
        int matrix[][] = new int[row][col];
        int count;
        System.out.println("Лабораторная работа №4");
        System.out.println("Задание: ...");
        System.out.println("Подсчитать количество отрицательных элементов в таблице и увеличить на это значение минимальный и максимальный элементы таблицы");

        try {
            /**
             * Part 1
             */

            //Текущий каталог xml файла
            String FileName = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath() + System.getProperty("file.separator") + "nak_data.xml";
            Properties props = new Properties(); // Переменная хранит xml file.
            File file = new File(FileName); // Переменная для доступа к файлу

            if (!file.exists()) // Если nak_data.xml не существует то..
            {
                file.createNewFile(); // Создаем пустой nak_data.xml

                for (int i = 0; i < row; i++) // Генерируем рандомные числа
                {
                    for (int j = 0; j < col; j++) {
                        count = (int) Math.round((Math.random() * 18) - 9);
                        props.setProperty("matrix" + i + j, String.valueOf(count));
                    }
                }
                // Сохранение обработанных данных массива в XML-файл
                props.storeToXML(new FileOutputStream(FileName), new Date().toString());
            } else // Если существует то...
            {
                props.loadFromXML(new FileInputStream(FileName));
            }

            /**
             * End Part 1
             */
            /**
             * Part 2 Считывание данных из XML-файла
             */
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    count = Integer.valueOf(props.getProperty("matrix" + i + j, "0"));
                    matrix[i][j] = count;
                    System.out.print(count + " ");
                }
                System.out.println("");
            }

            /**
             * End Part 2
             */
            /**
             * Part 3 Алгоритм
             */
            int mini = 0, minj = 0, maxi = 0, maxj = 0;
            int max = matrix[maxi][maxj], min = matrix[mini][minj], maxElement = 0, minElement = 0;

            int nCount = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    //System.out.print(matrix[i][j] + " ");
                    // Thread.sleep(10);
                    count = matrix[i][j];

                    if (count < 0) {
                        nCount = nCount + 1;
                    }

                    if (count < min) {
                        min = matrix[i][j];
                        mini = i;
                        minj = j;
                    }
                    if (count > max) {
                        max = matrix[i][j];
                        maxi = i;
                        maxj = j;
                    }
                }

            }
            minElement = nCount + min; // Сумма кол-во отрицательных чисел и минимального элемента в матрице
            maxElement = nCount + max; // Сумма кол-во отрицательных чисел и максимального элемента в матрице

            matrix[maxi][maxj] = maxElement; // И заменяем его на сумму отриц+мин элемента

            matrix[mini][minj] = minElement;

            System.out.println(" ----- ");
            /*
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println("");
            }

             */

            System.out.println("Min Element " + min);
            System.out.println("Max Element " + max);

            System.out.println("Кол-во отрицательных чисел " + nCount);
            System.out.println(" ");
            /* End Part 3*/

            /*Part 4*/
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    // Сохраняем данные в переменную, хранящую данные xml
                    props.put("matrix" + i + j, String.valueOf(matrix[i][j]));
                }
            }
            // Сохранение обработанных данных массива в XML-файл
            props.storeToXML(new FileOutputStream(FileName), new Date().toString());

            System.out.println("Новая матрица");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(props.getProperty("matrix" + i + j, "?") + " ");
                }
                System.out.println("");
            }

            /*End Part 4 */
        } catch (Exception e) {
            System.err.println("Error 404");
        }

    }
}
