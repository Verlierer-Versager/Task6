package HashMapImplementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(System.in);
        String fileName = scn.nextLine();
        File file = new File(fileName);
        Scanner in = new Scanner(new File(fileName));
        String text = in.nextLine();
        //C:\Users\sestr\IdeaProjects\Task6aisd\src\Text.txt
        Task task = new Task();
        ArrayList<Occurrence> list = task.solution(text);
        for (Occurrence elem : list) {
            System.out.println(elem.getChars() + " = " + elem.getOccurrence());
        }
    }
}
