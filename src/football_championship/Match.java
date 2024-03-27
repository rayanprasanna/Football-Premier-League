package football_championship;

public class Match {
    private Date date;
    private String club1Name, club2Name;
    private int club1Score, club2Score;

    public Match(Date date, String club1Name, String club2Name, int club1Score, int club2Score) {
        this.date = date;
        this.club1Name = club1Name;
        this.club2Name = club2Name;
        this.club1Score = club1Score;
        this.club2Score = club2Score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClub1Name() {
        return club1Name;
    }

    public void setClub1Name(String club1Name) {
        this.club1Name = club1Name;
    }

    public String getClub2Name() {
        return club2Name;
    }

    public void setClub2Name(String club2Name) {
        this.club2Name = club2Name;
    }

    public int getClub1Score() {
        return club1Score;
    }

    public void setClub1Score(int club1Score) {
        this.club1Score = club1Score;
    }

    public int getClub2Score() {
        return club2Score;
    }

    public void setClub2Score(int club2Score) {
        this.club2Score = club2Score;
    }
}
