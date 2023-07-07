package advisor;

import java.util.Scanner;
import java.util.stream.Stream;

public class Advisor {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String URL = "https://accounts.spotify.com/authorize?"
            + "client_id=e5c95267adf941d58b7b22b2889ac065"
            + "redirect_uri=https://www.example.com&"
            + "response_type=code";
    private static boolean isAuthorized = false;

    public void run() {
        while (true) {
            String command = SCANNER.nextLine();
            if (command.equals("exit")) {
                System.out.println("---GOODBYE!---");
                break;
            } else if (command.equals("auth")) {
                System.out.println(URL);
                System.out.println("---SUCCESS---");
                isAuthorized = true;
            }
            processCommand(command);
        }
    }

    private void processCommand(String command) {
        if (!isAuthorized) {
            System.out.println("Please, provide access for application.");
        } else {
            if (command.equals("new")) {
                System.out.println("---NEW RELEASES---");
            } else if (command.equals("featured")) {
                System.out.println("---FEATURED---");
            } else if (command.equals("categories")) {
                System.out.println("---CATEGORIES---");
            } else if (command.contains("playlist")) {
                String playlistName = getPlaylistName(command);
                System.out.printf("---%s PLAYLISTS---", playlistName.toUpperCase());
            }
        }
    }

    private String getPlaylistName(String command) {
        return Stream.of(command.split("\\s+"))
                .reduce((first, second) -> second)
                .orElse("");
    }
}


