package advisor;

public class Main {

    public static void main(String[] args) {
        if (args.length == 2 && args[0].equals("-access")) {
            Configuration.SERVER_PATH = args[1];
        }
        SpotifyAdvisor advisor = new SpotifyAdvisor();
        advisor.start();
    }
}

