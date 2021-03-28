package communication;

public class FriendlyApp {
    public static void main(String[] args) {
        switch (args[0]) {
            case "Hello":
                System.out.println("Hello:)");
                break;
            case "Bye":
                System.err.println("Are you leaving already?:(");
                break;
            case "Count":
                System.out.println("One");
                System.out.println("Two");
                System.out.println("Three");
                break;
            default:
                System.err.println("I don't understand you:(");
                break;
        }
    }
}
