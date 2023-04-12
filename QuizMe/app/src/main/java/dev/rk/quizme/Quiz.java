package dev.rk.quizme;

import java.util.Arrays;
import java.util.List;

public class Quiz {
    public String image;
    public String question;
    public List<String> answers;
    public String rightAnswer;

    public Quiz(String question, List<String> answers, String rightAnswer, String image) {
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.image = image;
    }

    public static List<Quiz> defaultQuizList() {
        List<String> q1answers = Arrays.asList("Nassau", "Buenos Aires", "Yerevan", "Vienna");
        Quiz q1 = new Quiz("Capital of Austria", q1answers, "Vienna",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Flag_of_Austria.svg/383px-Flag_of_Austria.svg.png");

        List<String> q2answers = Arrays.asList("Brussels", "Belmopan", "Thimphu", "Porto-Novo");
        Quiz q2 = new Quiz("Capital of Belgium", q2answers, "Brussels",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Flag_of_Belgium.svg/294px-Flag_of_Belgium.svg.png");

        List<String> q3answers = Arrays.asList("Bujumbura", "Sofia", "Phnom Penh", "Praia");
        Quiz q3 = new Quiz("Capital of Bulgaria", q3answers, "Sofia",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Flag_of_Bulgaria.svg/383px-Flag_of_Bulgaria.svg.png");

        List<String> q4answers = Arrays.asList("San Jose", "Yamoussoukro", "Yaounde", "Santiago");
        Quiz q4 = new Quiz("Capital of Chile", q4answers, "Santiago",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Flag_of_Chile.svg/383px-Flag_of_Chile.svg.png");

        List<String> q5answers = Arrays.asList("Nicosia", "Bogota", "Havana", "Moroni");
        Quiz q5 = new Quiz("Capital of Cuba", q5answers, "Havana",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Flag_of_Cuba.svg/383px-Flag_of_Cuba.svg.png");

        List<String> q6answers = Arrays.asList("Malabo", "Cairo", "Asmara", "San Salvador");
        Quiz q6 = new Quiz("Capital of Egypt", q6answers, "Cairo",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Flag_of_Egypt.svg/383px-Flag_of_Egypt.svg.png");

        List<String> q7answers = Arrays.asList("Asmara", "Suva", "Tallinn", "Helsinki");
        Quiz q7 = new Quiz("Capital of Estonia", q7answers, "Tallinn",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Flag_of_Estonia.svg/383px-Flag_of_Estonia.svg.png");

        List<String> q8answers = Arrays.asList("Tbilisi", "Accra", "Libreville", "Paris");
        Quiz q8 = new Quiz("Capital of Georgia", q8answers, "Tbilisi",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Flag_of_Georgia.svg/383px-Flag_of_Georgia.svg.png");

        List<String> q9answers = Arrays.asList("Accra", "Saint George's", "Athens", "Berlin");
        Quiz q9 = new Quiz("Capital of Greece", q9answers, "Athens",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/383px-Flag_of_Greece.svg.png");

        List<String> q10answers = Arrays.asList("Baghdad", "Budapest", "New Delhi", "Reykjavik");
        Quiz q10 = new Quiz("Capital of Iceland", q10answers, "Reykjavik",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Flag_of_Iceland.svg/354px-Flag_of_Iceland.svg.png");

        return Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8, q9, q10);
    }
}
