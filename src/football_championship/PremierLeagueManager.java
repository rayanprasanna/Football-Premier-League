package football_championship;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PremierLeagueManager implements LeagueManager{
    //Max number of clubs that can belong to English Premier League
    private final int LEAGUE_CAPACITY = 20;
    //create empty list of club
    List<FootballClub> clubList = new ArrayList<>(LEAGUE_CAPACITY);
    //Max number of match can be played in one season in English Premier League
    private final int MATCHES_CAPACITY = 38;
    //create empty list of matches
    List<Match> matchList = new ArrayList<>(MATCHES_CAPACITY);
    @Override
    public int getNumberOfFootballClubs() {
        return clubList.size();
    }
    //Menu Design
    private void menuDesign(){
        System.out.println(
                """
                        To continue, choose option from the menu:
                        -----------------------------------------
                        Q:\t Quit program
                        C:\t Create a new club and add to the league
                        R:\t Remove club from Premier League
                        D:\t Details & stats of a club
                        T:\t Table of the Premier League
                        M:\t Add a match score
                        S:\t Save data in to file
                        """);
    }
    // Menu methods
    String mainMenu(){
        menuDesign();
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();
        while (wrongMenuInput(choice)){
            System.out.println("\nInvalid input!");
            menuDesign();
            choice = scanner.nextLine().toLowerCase();
        }
        return choice;
    }
    void createClub(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type name of the club: ");
        String name = scanner.nextLine();

        //Validate input
        while (nameValidationFailed(name)){
            name = scanner.nextLine();
        }
        System.out.println("Type club's location: ");
        String location = scanner.nextLine();

        //Validation input
        while (nameValidationFailed(location)){
            location = scanner.nextLine();
        }
        //Add new instance of a FootballClub to the club list
        clubList.add(new FootballClub(name, location));
        //Confirm that added successfully
        System.out.println(STR."...\n\{name} club has been added!");
        displayClubInfo();
    }
    void removeClub(){
        Scanner scanner = new Scanner(System.in);
        //Display current clubs
        displayClubNames(clubList);
        System.out.println("Which club to REMOVE: ");
        String name = scanner.nextLine();

        //Validate input
        while (clubNotInLeague(name)){
            System.out.println("Wrong club name!\nTry again: ");
            name = scanner.nextLine();
        }
        //Remove club
        Iterator<FootballClub> iterator = clubList.iterator();
        while (iterator.hasNext()){
            FootballClub club = iterator.next();
            if (club.getName().equals(name)){
                iterator.remove();
                System.out.println(STR."...\n\{name} removed!");
            }
        }
        displayClubInfo();
    }

    void displayClubStats(){
        Scanner scanner = new Scanner(System.in);
        //Display current clubs
        displayClubNames(clubList);
        System.out.println("Name of the club: ");
        String name = scanner.nextLine();

        //validate input
        while (clubNotInLeague(name)){
            System.out.println("Wrong club name!\nTry again: ");
            name = scanner.nextLine();
        }
        //Display clubs info
        for (FootballClub club : clubList) {
            if (club.getName().equals(name)) {
                System.out.println(
                        STR."...\n\{name} Club from \{club.getLocation()}:\n---------------------------\nMatches played:\t \{club.getNumberOfMatchPlayed()}\nWins:\t\t\t \{club.getWins()}\nDraws:\t\t\t \{club.getDraws()}\nDefeats:\t\t \{club.getDefeats()}\nGoals scored:\t \{club.getGoalsFor()}\nGoals received:\t \{club.getGoalsAgainst()}\nTOTAL POINTS:\t \{club.getPoints()}\n"
                );
            }
        }
    }
    void displayPremierLeagueTable(){
        System.out.format("                \t CLUB  |  MP   W   D   L  GF  GA  GD  Pts\n");
        List<FootballClub> clubsCopy = new ArrayList<>(clubList.stream().toList());
        // Selection Sort to display clubs row in the right order
        for (int i = 0; i < clubsCopy.size() - 1; i++){
            FootballClub firstUnsorted = clubsCopy.get(i);
            FootballClub bestClub = firstUnsorted;
            int index = i;

            for (int j = i+1; j < clubsCopy.size(); j++){
                FootballClub next = clubsCopy.get(j);
                if (bestClub.getPoints() == next.getPoints()){
                    //Resolve same points with goal difference
                    if ( bestClub.getGoalDifference() < next.getGoalDifference()){
                        bestClub = next;
                        index = j;
                    }
                }
                if (bestClub.getPoints() < next.getPoints()){
                    bestClub = next;
                    index = j;
                }
            }
            FootballClub sorted = bestClub;
            clubsCopy.set(index, firstUnsorted);
            clubsCopy.set(i, sorted);
            //Print out club with the highest point directly
            displayRow(sorted);
            // After the last iteration, display the last element
            if (i == clubsCopy.size() - 2){
                displayRow(clubsCopy.get(i + 1));
            }
        }
    }
    void addMatch(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type date of the match (dd-mm-yyyy): ");
        String date = scanner.nextLine();
        while (wrongDateFormat(date)){
            System.out.print("Wrong date format!\nTry again: ");
            date = scanner.nextLine();
        }
        // Display current clubs
        displayClubNames(clubList);
        // Take and validate input for clubs names
        System.out.print("Type name of the club: ");
        String club1Name = scanner.nextLine();
        while (clubNotInLeague(club1Name)) {
            System.out.print("Wrong club name!\nTry again: ");
            club1Name = scanner.nextLine();
        }
        System.out.print("Who they played against?: ");
        String club2Name = scanner.nextLine();
        while (clubNotInLeague(club2Name)) {
            System.out.print("Wrong club name!\nTry again: ");
            club2Name = scanner.nextLine();
        }
        System.out.print(STR."How many goals scored \{club1Name}?: ");
        // First score validation
        String club1ScoreAsString = scanner.nextLine();
        while (isNotNumberInRange(club1ScoreAsString)) {
            System.out.print("Did you type a natural number? (0-99)\nTry again: ");
            club1ScoreAsString = scanner.nextLine();
        }
        int club1Score = Integer.parseInt(club1ScoreAsString);
        System.out.print(STR."How many goals scored \{club2Name}?: ");
        // Second score validation
        String club2ScoreAsString = scanner.nextLine();
        while (isNotNumberInRange(club2ScoreAsString)) {
            System.out.print("Did you type a natural number? (0-99)\nTry again: ");
            club2ScoreAsString = scanner.nextLine();
        }
        int club2Score = Integer.parseInt(club2ScoreAsString);
        // Process input to update clubs statistics
        if (club1Score == club2Score) {
            updateClubsDraw(club1Name, club2Name, club1Score);
        } else if (club1Score > club2Score) {
            updateClubsWinLose(club1Name, club2Name, club1Score, club2Score);
        } else {
            updateClubsWinLose(club2Name, club1Name, club2Score, club1Score);
        }
        // Add match to the matches list
        matchList.add(new Match(new Date(date), club1Name, club2Name, club1Score, club2Score));
        System.out.println(STR."... Adding\n\{club1Name}\t\{club1Score}\t[\{date}]\t\{club2Score}\t\{club2Name}\nThe match has been added!");
    }

    void saveClubsDetailsInToFile(){
        //Saving list of clubs
        try{
            File file = new File(STR.".\{File.separator}clubs_list.txt");
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            for (FootballClub club : clubList) {
                writer.write(STR."\{club.getName()}\n\{club.getLocation()}\n\{club.getNumberOfMatchPlayed()}\n\{club.getWins()}\n\{club.getDraws()}\n\{club.getDefeats()}\n\{club.getGoalsFor()}\n\{club.getGoalsAgainst()}\n\{club.getGoalDifference()}\n\{club.getPoints()}\n");
            }
            writer.close();
            System.out.println("...\nClubs list has benn saved!");
        }catch (Exception error){
            System.out.println(STR."Exception error:\n\{error}");
        }
    }
    void savePlayedMatchesDetailsInToFile(){
        //Saving list of played matches
        try {
            File file = new File(STR.".\{File.separator}played_matches.txt");
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            FileWriter writer = new FileWriter(file);

            for (Match match : matchList) {
                writer.write(STR."\{match.getDate()}\n\{match.getClub1Name()}\n\{match.getClub2Name()}\n\{match.getClub1Score()}\n\{match.getClub2Score()}\n");
            }
            writer.close();
            System.out.println("...\nMatches data has been saved!");
        }catch (Exception error){
            System.out.println(STR."Exception error:\n\{error}");
        }
    }
    //Additional methods for update previous details when application start next time
    void loadDataFromFile(){
        try {
            // Load clubs list
            String path = System.getProperty("user.dir");
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(STR."\{path}\{File.separator}clubs_list.txt")));
            while (readFile.hasNext()) {
                String name = readFile.nextLine();
                String location = readFile.nextLine();
                FootballClub club = new FootballClub(name, location);
                club.setNumberOfMatchPlayed(Integer.parseInt(readFile.nextLine()));
                club.setWins(Integer.parseInt(readFile.nextLine()));
                club.setDraws(Integer.parseInt(readFile.nextLine()));
                club.setDefeats(Integer.parseInt(readFile.nextLine()));
                club.setGoalsFor(Integer.parseInt(readFile.nextLine()));
                club.setGoalsAgainst(Integer.parseInt(readFile.nextLine()));
                club.setGoalDifference(Integer.parseInt(readFile.nextLine()));
                club.setPoints(Integer.parseInt(readFile.nextLine()));
                clubList.add(club);
            }
            readFile.close();
            System.out.println("... Clubs has been loaded!");
        }
        catch (FileNotFoundException error) {
            System.out.println("Exception error:\nNo data to load!\nAdd and save data first, then reopen the simulator.\n");
        }
        // Load matches list
        try {
            String path = System.getProperty("user.dir");
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(STR."\{path}\{File.separator}played_matches.txt")));
            while (readFile.hasNext()) {
                String date = readFile.nextLine();
                String club1Name = readFile.nextLine();
                String club2Name = readFile.nextLine();
                int club1Score = Integer.parseInt(readFile.nextLine());
                int club2Score = Integer.parseInt(readFile.nextLine());
                Match match = new Match(new Date(date), club1Name, club2Name, club1Score, club2Score);
                match.setDate(new Date(date));
                match.setClub1Name(club1Name);
                match.setClub2Name(club2Name);
                match.setClub1Score(club1Score);
                match.setClub2Score(club2Score);
                matchList.add(match);
            }
            readFile.close();
            System.out.println("... Matches history has been loaded!\n");
        }
        catch (FileNotFoundException error) {
            System.out.println("Exception error:\nNo data to load!\nAdd and save data first, then reopen the simulator.\n");
        }
    }
    private void updateClubsWinLose(String winner, String loser, int winnerScore, int loserScore) {
        for (FootballClub club : clubList) {
            if (club.getName().equals(winner)) {
                club.setNumberOfMatchPlayed(club.getNumberOfMatchPlayed() + 1);
                club.setWins(club.getWins() + 1);
                club.setGoalsFor(club.getGoalsFor() + winnerScore);
                club.setGoalsAgainst(club.getGoalsAgainst() + loserScore);
                club.setGoalDifference(club.getGoalDifference() + (winnerScore - loserScore));
                club.setPoints(club.getPoints() + 3);
            }
        }
        for (FootballClub club : clubList) {
            if (club.getName().equals(loser)) {
                club.setNumberOfMatchPlayed(club.getNumberOfMatchPlayed() + 1);
                club.setDefeats(club.getDefeats() + 1);
                club.setGoalsFor(club.getGoalsFor() + loserScore);
                club.setGoalsAgainst(club.getGoalsAgainst() + winnerScore);
                club.setGoalDifference(club.getGoalDifference() + (loserScore - winnerScore));
            }
        }
    }

    private void updateClubsDraw(String club1Name, String club2Name, int score) {

        for (FootballClub club : clubList) {
            if (club.getName().equals(club1Name)) {
                club.setNumberOfMatchPlayed(club.getNumberOfMatchPlayed() + 1);
                club.setDraws(club.getDraws() + 1);
                club.setPoints(club.getPoints() + 1);
                club.setGoalsFor(club.getGoalsFor() + score);
                club.setGoalsAgainst(club.getGoalsAgainst() + score);
            }
        }

        for (FootballClub club : clubList) {
            if (club.getName().equals(club2Name)) {
                club.setNumberOfMatchPlayed(club.getNumberOfMatchPlayed() + 1);
                club.setDraws(club.getDraws() + 1);
                club.setPoints(club.getPoints() + 1);
                club.setGoalsFor(club.getGoalsFor() + score);
                club.setGoalsAgainst(club.getGoalsAgainst() + score);
            }
        }
    }

    private void displayRow(FootballClub club) {
        System.out.format("%25s" + "%7d" + "%4d" + "%4d" + "%4d" + "%4d" + "%4d" + "%4d" + "%4d\n",
                club.getName(),
                club.getNumberOfMatchPlayed(),
                club.getWins(),
                club.getDraws(),
                club.getDefeats(),
                club.getGoalsFor(),
                club.getGoalsAgainst(),
                club.getGoalDifference(),
                club.getPoints()
        );
    }

    private void displayClubNames(List<FootballClub> clubList) {
        System.out.println("Current clubs list: ");
        clubList.forEach(club -> {
            if (clubList.indexOf(club) < clubList.size()){
                System.out.println(STR."\{club.getName()}, ");
            }else {
                System.out.println(club.getName());
            }
        });
    }

    private void displayClubInfo() {
        System.out.println(STR."There are now \{getNumberOfFootballClubs()} clubs in the Premier League.");
    }

    //Validation methods
    private boolean nameValidationFailed(String name) {
        if (name.isEmpty()){
            System.out.println("Name cannot be empty!\n Try again: ");
            return true;
        }
        if (isInteger(name)){
            System.out.println("Name cannot be a number!\n Try again: ");
            return true;
        }
        return false;
    }

    private boolean isInteger(String name) {
        try {
            Integer.parseInt(name);
            return true;
        }catch (NumberFormatException error){
            return false;
        }
    }

    private boolean clubNotInLeague(String name) {
        for (FootballClub club : clubList) {
            if (club.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
    private boolean wrongDateFormat(String date) {
        return !Pattern.matches("[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]", date);
    }
    private boolean isNotNumberInRange(String club1ScoreAsString) {
        try {
            int number = Integer.parseInt(club1ScoreAsString);
            return number < 0 || number >= 100;
        } catch (NumberFormatException error) {
            return true;
        }
    }
    private boolean wrongMenuInput(String input){
        return !"q".equals(input)
                && !"c".equals(input)
                && !"r".equals(input)
                && !"d".equals(input)
                && !"t".equals(input)
                && !"m".equals(input)
                && !"s".equals(input);
    }
}
