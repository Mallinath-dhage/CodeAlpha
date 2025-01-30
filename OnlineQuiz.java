import java.util.*;

class User {
    private String username;
    private String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}

class Question {
    String question;
    List<String> options;
    int correctOption;
    
    public Question(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class OnlineQuiz {
    private static final Map<String, User> users = new HashMap<>();
    private static final List<Question> questions = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        users.put("Shivaraju", new User("Shivaraju", "@shiva231"));
        users.put("admin", new User("admin", "admin123"));
        
        questions.add(new Question("What is the capital of France?", Arrays.asList("Berlin", "Madrid", "Paris", "Rome"), 3));
        questions.add(new Question("Which data structure uses LIFO?", Arrays.asList("Queue", "Stack", "Linked List", "Tree"), 2));
        questions.add(new Question("What is the largest planet in our solar system?", Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), 3));
        questions.add(new Question("What is the powerhouse of the cell?", Arrays.asList("Nucleus", "Mitochondria", "Ribosome", "Endoplasmic Reticulum"), 2));
        questions.add(new Question("What is the largest mammal in the world?", Arrays.asList("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"), 2));

        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();
        
        if (!users.containsKey(username) || !users.get(username).authenticate(password)) {
            System.out.println("Invalid credentials! Exiting...");
            return;
        }
        
        System.out.println("Login successful! Starting quiz...");
        
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.question);
            for (int j = 0; j < q.options.size(); j++) {
                System.out.println((j + 1) + ". " + q.options.get(j));
            }
            System.out.print("Your answer: ");
            int answer = sc.nextInt();
            
            if (answer == q.correctOption) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong !! Correct answer: " + q.options.get(q.correctOption - 1) + "\n");
            }
        }
        
        System.out.println("Quiz over! Your final score: " + score + "/" + questions.size());
        sc.close();
    }
}
