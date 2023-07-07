package advisor;

import java.util.Scanner;
import java.util.stream.Stream;

public class SpotifyAdvisor {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static boolean isAuthorized = false;

    public void start() {

        while (true) {
            String command = SCANNER.nextLine();
            if (command.equals("exit")) {
                System.out.println("---GOODBYE!---");   
                break;
            } else if (command.equals("auth")) {
                auth();
                isAuthorized = true;
            }
            processCommand(command);
        }
    }

    private void processCommand(String command) {
        if (isAuthorized) {
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
        } else {
            System.out.println("Please, provide access for application.");
        }
    }

    private String getPlaylistName(String command) {
        return Stream.of(command.split("\\s+"))
                .reduce((first, second) -> second)
                .orElse("");
    }

    private void auth() {
        Server auth = new Server();
        auth.serverCreate();
        auth.accessToken();
    }
}
