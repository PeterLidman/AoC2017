package advent19;

import java.nio.file.Files;
import java.nio.file.Paths;

public class advent19 {
    static String[] s = new String[2005];
    static int antal = 0;
    static int dir = 0;//N=0 E=1 S=2 W=3
    static int posX = 0;
    static int posY = 0;
    static String word="";
    static int steps=0;

    public static void main(String[] args) throws Exception {
        Files.readAllLines(Paths.get("C:\\git\\AoC2017\\Advent19\\src\\advent19\\maze.txt")).forEach(e -> ins(e));
        //print();
        while (s[posY].charAt(posX) == ' ') {
            posX++;
        }
        dir = 2;

        do {
            if (Character.isLetter(s[posY].charAt(posX)) ) {
                word += s[posY].charAt(posX);
            }
            step();
            steps++;
            if (s[posY].charAt(posX) == '+') {
                turn();
            }
        } while ( s[posY].charAt(posX) != ' ');
        System.out.println("Part 1: Word= " + word + " Part 2: steps= " + steps);
    }

    static void turn() {
        switch (dir) {
        case 0:
        case 2:
            if(s[posY].charAt(posX+1) == ' ' ) {
                dir=3;
            } else {
                dir=1;
            }
            break;
        case 1:
        case 3:
            if(s[posY+1].charAt(posX) == ' ' ) {
                dir=0;
            } else {
                dir=2;
            }
            break;
        }
    }
    
    static void step() {
        switch (dir) {
        case 0:
            posY--;
            break;
        case 1:
            posX++;
            break;
        case 2:
            posY++;
            break;
        case 3:
            posX--;
            break;
        }
    }
    
    static void print() {
        for (int i = 0; i < antal; i++) {
            System.out.println((i + 1) + "/" + antal + ":" + s[i]);
        }
    }

    static void ins(String in) {
        s[antal++] = in;
    }
}
