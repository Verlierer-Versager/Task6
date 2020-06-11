package HashMapImplementation;

import java.util.ArrayList;

import java.util.Collections;

public class Task {


    public ArrayList<Occurrence> solution(String text) {
        ArrayList<Occurrence> list = new ArrayList<>();
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (isLetter(chars[i]) && isLetter(chars[i + 1])) {
                String letters = "" + chars[i] + chars[i + 1];
                if (map.containsKey(letters)) {
                    map.put(letters, map.get(letters) + 1);
                } else {
                    list.add(new Occurrence(letters, 0));
                    map.put(letters, 1);
                }
            }
        }
        int size = map.size();
        System.out.println(size);
        for (Occurrence element : list) {
            element.setOccurrence((double) map.get(element.getChars()) / size);
        }
        Collections.sort(list);
        return list;
    }

    private boolean isLetter(char ch) {
        return (ch <= 'Z' && ch >= 'A') || (ch <= 'z' && ch >= 'a')||ch=='Ё'||ch=='ё'
                || (ch <= 'я' && ch >= 'а') || (ch <= 'Я' && ch >= 'А');
    }
}
