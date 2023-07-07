package advisor;

public class Main {

    public static void main(String[] args) {
        if (args.length == 2 && args[0].equals("-access")) {
            Config.SERVER_PATH = args[1];
        }
        Advisor advisor = new Advisor();
        advisor.run();
    }
}

