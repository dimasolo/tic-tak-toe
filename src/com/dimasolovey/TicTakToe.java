package com.dimasolovey;

/**
 * Created by dmitry.solovey on 20.03.2015.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TicTakToe {
    // Фрейм, панель для кнопок и шрифт для текста кнопок
    JFrame frame;
    JPanel mainPanel;
    Font buttonFont = new Font("sanserif", Font.BOLD, 55);
    // Кнопки для создания панели 3х3
    static JButton buttonA1 = new JButton();
    static JButton buttonA2 = new JButton();
    static JButton buttonA3 = new JButton();
    static JButton buttonB1 = new JButton();
    static JButton buttonB2 = new JButton();
    static JButton buttonB3 = new JButton();
    static JButton buttonC1 = new JButton();
    static JButton buttonC2 = new JButton();
    static JButton buttonC3 = new JButton();
    // Текстовое поле для вывода хода игры и результата
    static JTextField result = new JTextField();
    // Отдельный поток для хода компьютера
    static Thread t;
    // Список ходов игрока и компьютера
    static ArrayList<Integer> Moves = new ArrayList<Integer>();
    // Массив для хранения кнопок
    static ArrayList<JButton> buttons = new ArrayList<JButton>();
    // Хранит значение хода компьютера
    static int computerMove;
    // Отдельные массивы для ходов компьтера и игрока
    static int [][] player;
    static int[][] computer;
    // Счетчики количества ходов игрока и компьютера
    int countOfMovesPlayer = 0;
    int countOfMovesComputer = 0;
    // Общее количество ходов (игрока и компьютера)
    static int countOfMoves = 0;
    // Статус игрока и компютера (0 - игра продолжается; 1 - выигрыш)
    int statusOfPlayer = 0;
    int statusOfComputer = 0;
    // Метод увеличения количества ходов
    public synchronized static void setCountOfMoves() {
        countOfMoves = countOfMoves + 1;
    }
    // Метод получения количества ходов
    public synchronized static int getCountOfMoves() {
        return countOfMoves;
    }
    // Метод добавления значения хода (игрока или компьютера)
    public synchronized static void add (int t) {
        Moves.add(t);
    }
    public static void main(String[] args) {
        new TicTakToe().buildGUI();
        player = new int[3][3];
        computer = new int[3][3];
        t = new Thread(new ComputerPlayer());
    }
    // Метод построения графического пользовательского интерфейса
    public void buildGUI() {
        // Создаем фрейм, главную панель, добавляем слушателя событий кнопок и т.д.
        frame = new JFrame("Крестики-нолики");
        mainPanel = new JPanel(new GridLayout(3,3));
        buttonA1.setActionCommand("1");
        buttonA1.addActionListener(new ButtonListener());
        buttons.add(buttonA1);
        mainPanel.add(buttonA1);
        buttonA2.setActionCommand("2");
        buttonA2.addActionListener(new ButtonListener());
        buttons.add(buttonA2);
        mainPanel.add(buttonA2);
        buttonA3.setActionCommand("3");
        buttonA3.addActionListener(new ButtonListener());
        buttons.add(buttonA3);
        mainPanel.add(buttonA3);
        buttonB1.setActionCommand("4");
        buttonB1.addActionListener(new ButtonListener());
        buttons.add(buttonB1);
        mainPanel.add(buttonB1);
        buttonB2.setActionCommand("5");
        buttonB2.addActionListener(new ButtonListener());
        buttons.add(buttonB2);
        mainPanel.add(buttonB2);
        buttonB3.setActionCommand("6");
        buttonB3.addActionListener(new ButtonListener());
        buttons.add(buttonB3);
        mainPanel.add(buttonB3);
        buttonC1.setActionCommand("7");
        buttonC1.addActionListener(new ButtonListener());
        buttons.add(buttonC1);
        mainPanel.add(buttonC1);
        buttonC2.setActionCommand("8");
        buttonC2.addActionListener(new ButtonListener());
        buttons.add(buttonC2);
        mainPanel.add(buttonC2);
        buttonC3.setActionCommand("9");
        buttonC3.addActionListener(new ButtonListener());
        buttons.add(buttonC3);
        mainPanel.add(buttonC3);
        result.setText("Ваш ход!");
        frame.getContentPane().add(mainPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, result);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
    // Внутренний класс - слушатель событий кнопок
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Вызываем метод увеличения ходов
            setCountOfMoves();
            // Определяем какую кнопку нажал игрок
            int actionCommand = Integer.parseInt(event.getActionCommand());
            /*
            Если количество ходов нечетное (признак хода игрока), тогда инкрементируем количество ходов игрока и
            вносим в соответствующую позицию массива ходов игрока значение 1.
             */
            if (getCountOfMoves() % 2 != 0) {
                countOfMovesPlayer++;
                switch (actionCommand) {
                    case 1: {
                        player[0][0] = 1;
                        buttonA1.setFont(buttonFont);
                        buttonA1.setText("X");
                        buttonA1.setEnabled(false);
                        break;
                    }
                    case 2: {
                        player[0][1] = 1;
                        buttonA2.setFont(buttonFont);
                        buttonA2.setText("X");
                        buttonA2.setEnabled(false);
                        break;
                    }
                    case 3: {
                        player[0][2] = 1;
                        buttonA3.setFont(buttonFont);
                        buttonA3.setText("X");
                        buttonA3.setEnabled(false);
                        break;
                    }
                    case 4: {
                        player[1][0] = 1;
                        buttonB1.setFont(buttonFont);
                        buttonB1.setText("X");
                        buttonB1.setEnabled(false);
                        break;
                    }
                    case 5: {
                        player[1][1] = 1;
                        buttonB2.setFont(buttonFont);
                        buttonB2.setText("X");
                        buttonB2.setEnabled(false);
                        break;
                    }
                    case 6: {
                        player[1][2] = 1;
                        buttonB3.setFont(buttonFont);
                        buttonB3.setText("X");
                        buttonB3.setEnabled(false);
                        break;
                    }
                    case 7:{
                        player[2][0] = 1;
                        buttonC1.setFont(buttonFont);
                        buttonC1.setText("X");
                        buttonC1.setEnabled(false);
                        break;
                    }
                    case 8: {
                        player[2][1] = 1;
                        buttonC2.setFont(buttonFont);
                        buttonC2.setText("X");
                        buttonC2.setEnabled(false);
                        break;
                    }
                    case 9: {
                        player[2][2] = 1;
                        buttonC3.setFont(buttonFont);
                        buttonC3.setText("X");
                        buttonC3.setEnabled(false);
                        break;
                    }
                }
                // Добавляем в общий список ходов значение хода игрока
                add(actionCommand);
                // Если количество ходов игрока больше 3, проверяем выиграл ли игрок.
                if (countOfMovesPlayer >= 3) {
                    if (statusOfPlayer == 0) {
                        statusOfPlayer = check(player);
                    }
                }
            }
            // Если игрок еще не выиграл (statusOfPlayer == 0) и количество ходов игрока меньше 5, запускаем поток
            // для хода компьютера
            if (statusOfPlayer == 0 && countOfMovesPlayer < 5)t.run();
            // Если игрок выиграл (statusOfPlayer == 1) Выводим результат победы игрока
            if (statusOfPlayer == 1) {
                result.setText("Поздравляем! Вы выиграли!");
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
                // Ничья
            } else if (statusOfPlayer == 0 && countOfMovesPlayer >=5) {
                result.setText("Боевая ничья!");
                for (JButton b : buttons) {
                    b.setEnabled(false);
                }
            }
        }
    }
    // Метод проверки выигрышных комбинаций
    /*
    Если игрок или компьютер выиграл, метод возвращает 1, иначе 0
     */
    public int check(int[][] a) {
        // Счетчик комбинаций по строке
        int countRow = 0;
        // Счетчик комбинаций по столбцу
        int countColumn = 0;
        // Счетчик комбинаций по первой диагонали
        int countDiagonal1 = 0;
        // Счетчик комбинаций по второй диагонали
        int countDiagonal2 = 0;
        // Проверка по строкам и столбцам
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] == 1) countRow++;
                if (a[j][i] == 1) countColumn++;
            }
            if (countRow == 3 || countColumn == 3) {
                return 1;
            }
            countRow = 0;
            countColumn = 0;
        }
        // Проверка по первой диагонали
        for (int i = 0, j = 0; i < a.length ; i++) {
            if (a[i][j] == 1) countDiagonal1++;
            j++;
        }
        if (countDiagonal1 == 3) {
            return 1;
        }
        // Проверка по второй диагонали
        for (int i = 0, j = 2; i < a.length ; i++) {
            if (a[i][j] == 1) countDiagonal2++;
            j--;
        }
        if (countDiagonal2 == 3) {
            return 1;
        }
        return 0;
    }
}
// Класс для реализации хода игрока
class ComputerPlayer extends TicTakToe implements Runnable {
    public void run() {
    setCountOfMoves();
         /*
            Если количество ходов четное (признак хода компьтера), тогда инкрементируем количество ходов компьютера и
            вносим в соответствующую позицию массива ходов компьютера значение 1.
             */
        if (getCountOfMoves() % 2 == 0) {
            countOfMovesComputer++;
            computerMove =(int) (Math.random()*10);
            while(computerMove == 0 || Moves.contains(computerMove)) {
                if (countOfMovesComputer >=5) break;
                computerMove =(int) (Math.random()*10);
            }
            add(computerMove);

           switch (computerMove) {
                case 1: {
                    computer[0][0] = 1;
                    buttonA1.setFont(buttonFont);
                    buttonA1.setText("O");
                    buttonA1.setEnabled(false);
                    break;
                }
                case 2: {
                    computer[0][1] = 1;
                    buttonA2.setFont(buttonFont);
                    buttonA2.setText("O");
                    buttonA2.setEnabled(false);
                    break;
                }
                case 3: {
                    computer[0][2] = 1;
                    buttonA3.setFont(buttonFont);
                    buttonA3.setText("O");
                    buttonA3.setEnabled(false);
                    break;
                }
                case 4: {
                    computer[1][0] = 1;
                    buttonB1.setFont(buttonFont);
                    buttonB1.setText("O");
                    buttonB1.setEnabled(false);
                    break;
                }
                case 5: {
                    computer[1][1] = 1;
                    buttonB2.setFont(buttonFont);
                    buttonB2.setText("O");
                    buttonB2.setEnabled(false);
                    break;
                }
                case 6: {
                    computer[1][2] = 1;
                    buttonB3.setFont(buttonFont);
                    buttonB3.setText("O");
                    buttonB3.setEnabled(false);
                    break;
                }
                case 7:{
                    computer[2][0] = 1;
                    buttonC1.setFont(buttonFont);
                    buttonC1.setText("O");
                    buttonC1.setEnabled(false);
                    break;
                }
                case 8: {
                    computer[2][1] = 1;
                    buttonC2.setFont(buttonFont);
                    buttonC2.setText("O");
                    buttonC2.setEnabled(false);
                    break;
                }
                case 9: {
                    computer[2][2] = 1;
                    buttonC3.setFont(buttonFont);
                    buttonC3.setText("O");
                    buttonC3.setEnabled(false);
                    break;
                }

            }
            // Если количество ходов компьютера больше 3, проверяем выиграл ли компьютер.
            if (countOfMovesComputer >= 3) {
                if (statusOfComputer == 0) {
                    statusOfComputer = check(computer);
                }
            }
        }
        if (statusOfComputer == 1) {
            result.setText("Не унывайте. На этот раз сильнее компьтер.");
            for (JButton b : buttons) {
                b.setEnabled(false);
            }
        } else if (statusOfComputer == 0 && countOfMovesComputer >=5) {
            result.setText("Боевая ничья!");
            for (JButton b : buttons) {
                b.setEnabled(false);
            }
        }
    }
}