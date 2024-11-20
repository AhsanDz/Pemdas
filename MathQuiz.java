import java.util.Random;
import java.util.Scanner;

public class MathQuiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        int level = 1;
        int questions = 10;

        System.out.println("Selamat datang di Permainan Kuis Matematika!");
        System.out.println("Jawab soal berikut secepat mungkin. Setiap jawaban yang benar menambah poin, dan yang salah mengurangi poin.");

        for (int i = 0; i < questions; i++) {
            int num1 = random.nextInt(10 * level) + 1;
            int num2 = random.nextInt(10 * level) + 1;
            char operator = generateOperator(random);
            int correctAnswer = calculateAnswer(num1, num2, operator);

            System.out.printf("Soal %d: %d %c %d = ?\n", i + 1, num1, operator, num2);

            System.out.print("Jawaban kamu: ");
            int userAnswer = scanner.nextInt();

            if (userAnswer == correctAnswer) {
                System.out.println("Benar!");
                score += 10;
            } else {
                System.out.println("Salah! Jawaban yang benar adalah: " + correctAnswer);
                score -= 10;
            }

            if (i % 2 == 1) {
                level++;
            }
            System.out.println("Skor sementara : " + score);
        }

        System.out.println("Permainan selesai!");
        System.out.println("Skor akhir kamu: " + score);
    }

    private static char generateOperator(Random random) {
        char[] operators = {'+', '-', '*', '/'};
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
            default:
                return 0;
        }
    }
}