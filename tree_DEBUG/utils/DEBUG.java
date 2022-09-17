package tree_DEBUG.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DEBUG {
    public static enum Modes {
        DEBUG, VERBOSE;
    }

    public static Modes MODE = Modes.DEBUG;

    private DEBUG(){}

    public static void writeOutputInFile(String line){
        try (FileWriter arq = new FileWriter("saida_DEBUG.txt", true)) {
            try (PrintWriter gravarArq = new PrintWriter(arq)) {
                gravarArq.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
