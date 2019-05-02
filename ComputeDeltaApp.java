package computedeltaapp;

public class ComputeDeltaApp {

    private int M; // длина паттерна
    private int alphabet = 256; // размер алфавита
    private int[][] delta; // 2-мерная таблица переходов
    private String pattern; // паттерн
    private int[] prefix; // таблица префиксов

    public ComputeDeltaApp(String pat) {
        pattern = pat;
        M = pattern.length();
        delta = new int[M+1][alphabet]; // (длина паттерна) * число символов алфавита
        prefix = new int[M];
    }

    public void OUT() { // вывод конечного автомата
        System.out.println("--ComputeDelta--");
        System.out.print("   ");
        for (int j = 0; j < M; j++) {
            System.out.print(pattern.charAt(j) + "   ");
        }
        System.out.print("");
        System.out.println("°");
        for (int j = 0; j < alphabet; j++) {
            int sum = 0;
            for (int i = 0; i < M; i++) {
                sum = sum + delta[i][j];
            }
            if (sum != 0) {
                System.out.print((char) j + "  ");
                for (int i = 0; i < M+1; i++) {
                    System.out.print(delta[i][j]);
                    System.out.print("   ");
                }
                System.out.println("");
            }
        }
    }   
        
        public boolean isPrefix (String s1, String s2){
            boolean res = true;
            char [] S1 = s1.toCharArray();
            char [] S2 = s2.toCharArray();
            for (int i = 0; i < S1.length; i++) {
                System.out.println("s1 " + S1[i] + " s2 " + S2[i]);
                
                if (S1[i] != S2[i]) {
                    
                    res = false;
                    break;
                }
            }
            return res;
        }     

    public int[][] computeDelta() {
        prefix_function(); // таблица префиксов
        for (int q = 0; q < M+1; q++) { // двойной цикл 
            for (char a = 0; a < alphabet; a++) {
                int k;// = q; // состояние перехода
                
                if (q == M) {
                    k = 0;
                } else {
                    k = q;
                }                

                while (k > 0 && a != pattern.charAt(k)) { // откат состояния
                    k = prefix[k - 1];
                }
                if (a == pattern.charAt(k)) { // следующее состояние
                    k++;
                }
                delta[q][a] = k;
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
        for (int i = 0; i < M; i++) {
            System.out.print(prefix[i] + " , ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {        
        ComputeDeltaApp d1 = new ComputeDeltaApp("12345");
        d1.computeDelta();
        d1.OUT();       
        System.out.println("-------");
        d1 = new ComputeDeltaApp("ababac");
        d1.computeDelta();
        d1.OUT(); 
        System.out.println("-------");
        d1 = new ComputeDeltaApp("aaaaaa");
        d1.computeDelta();
        d1.OUT(); 
    }

}
