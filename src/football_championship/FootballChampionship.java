package football_championship;

public class FootballChampionship {
    public static void main(String[] args){
        System.out.println("Welcome to the Football Championship Simulator!\n");
        // Create Premier League instance
        PremierLeagueManager premierLeague = new PremierLeagueManager();
        // Load data from file if exists
        premierLeague.loadDataFromFile();
        // Current no of clubs in the league
        System.out.println("########  The season 2023-24 is ongoing!  ########");
        System.out.println(STR."There currently are \{premierLeague.getNumberOfFootballClubs()} clubs in the Premier League.");

        // Loading main menu:
        String menuChoice = premierLeague.mainMenu();
        while (!menuChoice.equals("q")) {
            switch (menuChoice) {
                case "c":
                    System.out.println("\nCreate new club:\n---------------------");
                    premierLeague.createClub();
                    menuChoice = premierLeague.mainMenu();
                    break;
                case "r":
                    System.out.println("\nRemove club from Premier League:\n---------------------");
                    premierLeague.removeClub();
                    menuChoice = premierLeague.mainMenu();
                    break;
                case "d":
                    System.out.println("\nDisplay details & stats of a club:\n---------------------");
                    premierLeague.displayClubStats();
                    menuChoice = premierLeague.mainMenu();
                    break;
                case "t":
                    System.out.println("\nDisplay Premier League table:\n--------------------------------------------------");
                    premierLeague.displayPremierLeagueTable();
                    menuChoice = premierLeague.mainMenu();
                    break;
                case "m":
                    System.out.println("\nAdd a played match:\n---------------------");
                    premierLeague.addMatch();
                    menuChoice = premierLeague.mainMenu();
                    break;
                case "s":
                    System.out.println("\nSave data to the file:\n---------------------");
                    premierLeague.saveClubsDetailsInToFile();
                    premierLeague.savePlayedMatchesDetailsInToFile();
                    menuChoice = premierLeague.mainMenu();
                    break;
                default:
                    System.out.println("\nInvalid input!");
                    premierLeague.mainMenu();
            }
        }
        // Closing the program:
        System.out.println("\nYour session has ended. Goodbye!");
    }
}
