import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class MathQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 100;
        int level = 1;
        int questions = 10;

        System.out.println("=========================================");
        System.out.println("  Selamat Datang di Permainan Kuis Matematika!");
        System.out.println("=========================================");
        System.out.println("Jawab soal berikut dengan memilih jawaban");
        System.out.println("yang benar dari pilihan yang tersedia.");
        System.out.println("-----------------------------------------");

        for (int i = 0; i < questions; i++) {
            int num1 = 0, num2 = 0, correctAnswer = 0;
            char operator = generateOperator(random, level);

            if (operator == '/') {
                do {
                    num2 = random.nextInt(10 * level) + 1;
                    num1 = num2 * (random.nextInt(10) + 1);
                } while (num2 == 0);
            } else if (operator == '^') {
                do {
                    num1 = random.nextInt(10) + 2;
                    num2 = random.nextInt(4) + 1;
                } while (Math.pow(num1, num2) > 2000);
            } else if (operator == '√') {
                int base = random.nextInt(10 * level) + 1;
                num1 = base * base;
            } else {
                num1 = random.nextInt(10 * level) + 1;
                num2 = random.nextInt(10 * level) + 1;
            }

            correctAnswer = calculateAnswer(num1, num2, operator);

            System.out.printf("\nSoal %d (Level %d):\n", i + 1, level);
            if (operator == '√') {
                System.out.printf(">> %c%d = ?\n", operator, num1);
            } else {
                System.out.printf(">> %d %c %d = ?\n", num1, operator, num2);
            }

            int[] options = generateOptions(random, correctAnswer);
            char[] choices = {'A', 'B', 'C', 'D'};

            System.out.println("Pilih jawaban:");
            for (int j = 0; j < options.length; j++) {
                System.out.printf("  %c. %d\n", choices[j], options[j]);
            }

            System.out.print("Jawaban kamu (A/B/C/D): ");
            String input = scanner.next().toUpperCase();
            while (input.length() != 1 || (input.charAt(0) < 'A' || input.charAt(0) > 'D')) {
                System.out.print("Input salah. Masukkan (A/B/C/D): ");
                input = scanner.next().toUpperCase();
            }
            char userChoice = input.charAt(0);
            int selectedAnswer = options[userChoice - 'A'];

            if (selectedAnswer == correctAnswer) {
                System.out.println("✅ Benar!");
            } else {
                System.out.println("❌ Salah! Jawaban yang benar adalah: " + correctAnswer);
                score -= 10;
            }

            if (i % 2 == 1) {
                level++;
                System.out.println("\n🎉 Selamat! Kamu naik ke level " + level + "!");
            }

            System.out.println("-----------------------------------------");
        }

        System.out.println("\n=========================================");
        System.out.println("            Permainan Selesai!");
        System.out.println("=========================================");
        System.out.println("Skor akhir kamu: " + score);
    }

    private static char generateOperator(Random random, int level) {
        if (level == 1) {
            return randomOperator(random, new char[]{'+', '-', '*', '/'});
        } else if (level == 2) {
            return randomOperator(random, new char[]{'+', '-', '*', '/', '^'});
        } else {
            return randomOperator(random, new char[]{'+', '-', '*', '/', '^', '√'});
        }
    }

    private static char randomOperator(Random random, char[] operators) {
        return operators[random.nextInt(operators.length)];
    }

    private static int calculateAnswer(int num1, int num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            case '^':
                return (int) Math.pow(num1, num2);
            case '√':
                return (int) Math.sqrt(num1);
            default:
                throw new IllegalArgumentException("Operator tidak valid: " + operator);
        }
    }

    private static int[] generateOptions(Random random, int correctAnswer) {
        int[] options = new int[4];
        int correctPosition = random.nextInt(4);

        for (int i = 0; i < options.length; i++) {
            if (i == correctPosition) {
                options[i] = correctAnswer;
            } else {
                int fakeAnswer;
                do {
                    fakeAnswer = correctAnswer + random.nextInt(21) - 10;
                } while (fakeAnswer == correctAnswer || fakeAnswer < 0 || contains(options, fakeAnswer));
                options[i] = fakeAnswer;
            }
        }
        return options;
    }

    private static boolean contains(int[] array, int value) {
        for (int num : array) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }
}
