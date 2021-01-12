package advent20;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class advent20 {
	private static final String PATTERN = "p=<(.*),(.*),(.*)>, v=<(.*),(.*),(.*)>, a=<(.*),(.*),(.*)>";
    static int antal = 0;
    static Long p[][] = new Long[2010][9];
    static double min = 0;
    static int minPos = 0;
    static int left = 0;
    static boolean collision[] = new boolean[2010];

    public static void main(String[] args) throws Exception {
        Files.readAllLines(Paths.get("C:\\git\\AoC2017\\Advent20\\src\\advent20\\particle.txt")).forEach(e -> ins2(e));
        //print();
        //part1 i det långa perspektivet betyder bara accelerationen roll, minst a-vektor vinner
        min = accVector(0);
        for (int i = 1; i < antal; i++) {
            //System.out.println(i + " " + accVector(i));
            if (accVector(i) < min) {
                min = accVector(i);
                minPos = i;
            }
        }
        System.out.println("Part 1: Particle nr= " + minPos + " at distance= " + min);
        //part2
        for (int j = 0; j < 1_000; j++) {
            for (int i = 0; i < antal; i++) {
                calculate(i);
            }
            markCollisions();
        }
        left=antal;
        for (int i = 0; i < antal; i++) {
            if (collision[i]) {
                left--;
            }
        }
        System.out.println("Part 2: Particles left= " + left);
    }

    static void markCollisions() {
        for (int i = 0; i < antal-1; i++) {
            if (!collision[i]) {
                for (int j = i+1; j < antal; j++) {
                    if (!collision[j] && p[i][0].equals(p[j][0]) && p[i][1].equals(p[j][1]) && p[i][2].equals(p[j][2])) {
                        //System.out.println("collision i j" + i + " " + j);
                        collision[i] = true;
                        collision[j] = true;
                    }
                }
            }
        }
    }

    static void print() {
        for (int i = 0; i < antal; i++) {
            System.out.println("nr=" + i + " p=<" + p[i][0] + "," + p[i][1] + "," + p[i][2] + ">, v=<" + p[i][3] + "," + p[i][4] + ","
                    + p[i][5] + ">, a=<" + p[i][6] + "," + p[i][7] + "," + p[i][8] + ">");
        }
    }

    static void calculate(int pos) {
        p[pos][3] += p[pos][6];
        p[pos][4] += p[pos][7];
        p[pos][5] += p[pos][8];
        p[pos][0] += p[pos][3];
        p[pos][1] += p[pos][4];
        p[pos][2] += p[pos][5];
    }

    static double accVector(int pos) {
        return Math.abs(p[pos][6]) + Math.abs(p[pos][7]) + Math.abs(p[pos][8]);
    }

    static double manhattanDistance(int pos) {
        return Math.abs(p[pos][0]) + Math.abs(p[pos][1]) + Math.abs(p[pos][2]);
    }

    static void ins2(String in) {// á la Råberg style
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(in);
        matcher.find();
        for (int i = 0; i < 9; i++) {
            p[antal][i] = Long.parseLong(matcher.group(i + 1));
        }
        antal++;
    }

    static void ins(String in) {
        collision[antal] = false;
        String tmp[] = in.split(", ");
        String tmp2[];
        tmp2 = tmp[0].split("[=,<>]+");
        p[antal][0] = Long.valueOf(tmp2[1]);
        p[antal][1] = Long.valueOf(tmp2[2]);
        p[antal][2] = Long.valueOf(tmp2[3]);
        tmp2 = tmp[1].split("[=,<>]+");
        p[antal][3] = Long.valueOf(tmp2[1]);
        p[antal][4] = Long.valueOf(tmp2[2]);
        p[antal][5] = Long.valueOf(tmp2[3]);
        tmp2 = tmp[2].split("[=,<>]+");
        p[antal][6] = Long.valueOf(tmp2[1]);
        p[antal][7] = Long.valueOf(tmp2[2]);
        p[antal][8] = Long.valueOf(tmp2[3]);
        antal++;
    }
}
