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
        delta = new int[M][alphabet]; // (длина паттерна) * число символов алфавита
        prefix = new int[M];
    }

    public void OUT() { // вывод конечного автомата
        System.out.println("--ComputeDelta--");
        System.out.print("   ");
        for (int j = 0; j < M; j++) {
            System.out.print(pattern.charAt(j) + "   ");
        }
        System.out.println("");
        for (int j = 0; j < alphabet; j++) {
            int sum = 0;
            for (int i = 0; i < M; i++) {
                sum = sum + delta[i][j];
            }
            if (sum != 0) {
                System.out.print((char) j + "  ");
                for (int i = 0; i < M; i++) {
                    System.out.print(delta[i][j]);
                    System.out.print("   ");
                }
                System.out.println("");
            }
        }
    }

    public int[][] computeDeltaFast() {
        delta[0][pattern.charAt(0)] = 1;
        for (int X = 0, j = 1; j < M; j++) { // расчет delta [j][], X - позиция отката
            for (int c = 0; c < alphabet; c++) {
                delta[j][c] = delta[X][c]; // копирование несовпадений
            }
            delta[j][pattern.charAt(j)] = j + 1; // оформление совпадений
            X = delta[X][pattern.charAt(j)]; // обновление для отката, следующее X на основе предыдущего
        }
        return delta;
    }

    public int[][] computeDelta() {
        prefix_function(); // таблица префиксов
        for (int q = 0; q < M; q++) { // двойной цикл 
            for (char a = 0; a < alphabet; a++) {
                int k = q; // состояние перехода
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
        
        ComputeDeltaApp d1 = new ComputeDeltaApp("ababac");
        d1.computeDelta();
        d1.OUT();
        System.out.println("-------");
        ComputeDeltaApp d2 = new ComputeDeltaApp("ababac");
        d2.computeDeltaFast();
        d2.OUT();
        System.out.println("-------");
        d1 = new ComputeDeltaApp("1134544");
        d1.computeDelta();
        d1.OUT();
        System.out.println("-------");
        d2 = new ComputeDeltaApp("1134544");
        d2.computeDeltaFast();
        d2.OUT();
        
        System.out.println("тест времени выполнения"); // рандом текст
        String sample = "His exquisite sincerity education shameless ten earnestly breakfast add. So we me unknown as improve hastily sitting forming. Especially favourable compliment but thoroughly unreserved saw she themselves. Sufficient impossible him may ten insensible put continuing. Oppose exeter income simple few joy cousin but twenty. Scale began quiet up short wrong in in. Sportsmen shy forfeited engrossed may can. \n" +
"\n" +
"Compliment interested discretion estimating on stimulated apartments oh. Dear so sing when in find read of call. As distrusts behaviour abilities defective is. Never at water me might. On formed merits hunted unable merely by mr whence or. Possession the unpleasing simplicity her uncommonly. \n" +
"\n" +
"Barton did feebly change man she afford square add. Want eyes by neat so just must. Past draw tall up face show rent oh mr. Required is debating extended wondered as do. New get described applauded incommode shameless out extremity but. Resembled at perpetual no believing is otherwise sportsman. Is do he dispatched cultivated travelling astonished. Melancholy am considered possession on collecting everything. \n" +
"\n" +
"Prepared do an dissuade be so whatever steepest. Yet her beyond looked either day wished nay. By doubtful disposed do juvenile an. Now curiosity you explained immediate why behaviour. An dispatched impossible of of melancholy favourable. Our quiet not heart along scale sense timed. Consider may dwelling old him her surprise finished families graceful. Gave led past poor met fine was new. \n" +
"\n" +
"That know ask case sex ham dear her spot. Weddings followed the all marianne nor whatever settling. Perhaps six prudent several her had offence. Did had way law dinner square tastes. Recommend concealed yet her procuring see consulted depending. Adieus hunted end plenty are his she afraid. Resources agreement contained propriety applauded neglected use yet. \n" +
"\n" +
"Indulgence announcing uncommonly met she continuing two unpleasing terminated. Now busy say down the shed eyes roof paid her. Of shameless collected suspicion existence in. Share walls stuff think but the arise guest. Course suffer to do he sussex it window advice. Yet matter enable misery end extent common men should. Her indulgence but assistance favourable cultivated everything collecting. \n" +
"\n" +
"Now residence dashwoods she excellent you. Shade being under his bed her. Much read on as draw. Blessing for ignorant exercise any yourself unpacked. Pleasant horrible but confined day end marriage. Eagerness furniture set preserved far recommend. Did even but nor are most gave hope. Secure active living depend son repair day ladies now. \n" +
"\n" +
"Ferrars all spirits his imagine effects amongst neither. It bachelor cheerful of mistaken. Tore has sons put upon wife use bred seen. Its dissimilar invitation ten has discretion unreserved. Had you him humoured jointure ask expenses learning. Blush on in jokes sense do do. Brother hundred he assured reached on up no. On am nearer missed lovers. To it mother extent temper figure better. \n" +
"\n" +
"Agreed joy vanity regret met may ladies oppose who. Mile fail as left as hard eyes. Meet made call in mean four year it to. Prospect so branched wondered sensible of up. For gay consisted resolving pronounce sportsman saw discovery not. Northward or household as conveying we earnestly believing. No in up contrasted discretion inhabiting excellence. Entreaties we collecting unpleasant at everything conviction. \n" +
"\n" +
"For who thoroughly her boy estimating conviction. Removed demands expense account in outward tedious do. Particular way thoroughly unaffected projection favourable mrs can projecting own. Thirty it matter enable become admire in giving. See resolved goodness felicity shy civility domestic had but. Drawings offended yet answered jennings perceive laughing six did far. \n" +
"";
        
        d1 = new ComputeDeltaApp(sample);
        long timeStartA1 = System.currentTimeMillis();
        d1.computeDeltaFast();
        long timeStopA1 = System.currentTimeMillis() - timeStartA1;
        System.out.println("время выполнения computeDelta " + timeStopA1 + " миллисекунд");
        
        d2 = new ComputeDeltaApp(sample);
        long timeStartA2 = System.currentTimeMillis();
        d2.computeDeltaFast();
        long timeStopA2 = System.currentTimeMillis() - timeStartA2;
        System.out.println("время выполнения computeDeltaFast " + timeStopA2 + " миллисекунд");

        
    }

}
