package computedeltaapp;

public class ComputeDeltaApp {

    private final int M; // длина паттерна
    private final int alphabet = 256; // размер алфавита
    private final int[][] delta; // 2-мерная таблица переходов
    private final String pattern; // паттерн
    private final int[] prefix; // таблица префиксов
    private final boolean flagAlphabet[];

    public ComputeDeltaApp(String pat) {
        pattern = pat;
        M = pattern.length();
        delta = new int[M + 1][alphabet]; // (длина паттерна) * число символов алфавита
        prefix = new int[M];
        flagAlphabet = new boolean[alphabet]; // таблица флагов - используемых символов в паттерне (уменьшаем обработку таблицы дельты)
    }

    public void calcAlphabet() { // инициализация таблицы флагов
        for (int i = 0; i < flagAlphabet.length; i++) {
            flagAlphabet[i] = false;
        }
        for (int i = 0; i < M; i++) {
            if (!flagAlphabet[pattern.charAt(i)]) {
                flagAlphabet[pattern.charAt(i)] = true;
            }
        }
    }

    public void OUT() { // вывод конечного автомата
        calcAlphabet();
        System.out.println("--ComputeDelta--");
        System.out.print("   ");
        for (int j = 0; j < M; j++) {
            System.out.print(pattern.charAt(j) + "   ");
        }
        System.out.print("");
        System.out.println("*");
        for (int j = 0; j < alphabet; j++) {
            if (flagAlphabet[j]) {
                System.out.print((char) j + "  ");
                for (int i = 0; i < M + 1; i++) {
                    System.out.print(delta[i][j]);
                    System.out.print("   ");
                }
                System.out.println("");
            }
        }
    }

    public int[][] computeDelta() {
        prefix_function(); // таблица префиксов
        calcAlphabet();
        for (int q = 0; q <= M; q++) { // двойной цикл 
            for (char a = 0; a < alphabet; a++) {
                if (flagAlphabet[a]) {
                    int k = q;

                    if (q == M) { //  q = M автомат повторяет переходы из предыдущего состояния delta(q-1)
                        delta[q][a] = delta[prefix[q - 1]][a];
                    } else {
                        while (k > 0 && a != pattern.charAt(k)) { // откат состояния
                            k = prefix[k - 1];
                        }
                        if (a == pattern.charAt(k)) { // следующее состояние
                            k++;
                        }
                        delta[q][a] = k;
                    }
                }
            }
        }
        return delta;
    }

    private void prefix_function() { // вычисление префиксов
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (pattern.substring(0, j).equals(pattern.substring(i - j + 1, i + 1))) {
                    prefix[i] = j;
                }
            }
        }
    }

    public void outPrefix() {
        prefix_function();
        System.out.println("prefix");
        System.out.print("   ");
        for (int i = 0; i < M; i++) {
            System.out.print(prefix[i] + " , ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        ComputeDeltaApp d1 = new ComputeDeltaApp("12345");
        d1.computeDelta();
        d1.outPrefix();
        d1.OUT();
        System.out.println("----------------------------------------");
        d1 = new ComputeDeltaApp("ababac");
        d1.computeDelta();
        d1.outPrefix();
        d1.OUT();
        System.out.println("----------------------------------------");
        d1 = new ComputeDeltaApp("aaaaaa");
        d1.computeDelta();
        d1.outPrefix();
        d1.OUT();
        System.out.println("----------------------------------------");
        d1 = new ComputeDeltaApp("aba");
        d1.computeDelta();
        d1.outPrefix();
        d1.OUT();
        System.out.println("----------------------------------------");
        d1 = new ComputeDeltaApp("abaabaababa");
        d1.computeDelta();
        d1.outPrefix();
        d1.OUT();
    }
}
