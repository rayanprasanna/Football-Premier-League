package football_championship;

public class FootballClub extends SportsClub{
    private int numberOfMatchPlayed, wins, draws, defeats, goalsAgainst, goalsFor, goalDifference, points;
    public FootballClub(String name, String location) {
        super(name, location);
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumberOfMatchPlayed() {
        return numberOfMatchPlayed;
    }

    public void setNumberOfMatchPlayed(int numberOfMatchPlayed) {
        this.numberOfMatchPlayed = numberOfMatchPlayed;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }
}
