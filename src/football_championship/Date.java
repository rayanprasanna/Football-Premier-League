package football_championship;

public class Date {
    private final int day;
    private final int month;
    private final int year;

    public Date(String date) throws IllegalArgumentException{
        String[] strings = date.split("-");
        if (strings.length != 3) throw new IllegalArgumentException();
        int dd = Integer.parseInt(strings[0]);
        int mm = Integer.parseInt(strings[1]);
        int yyyy = Integer.parseInt(strings[2]);
        if (isValid(yyyy, mm, dd)) throw new IllegalArgumentException();
        this.day = dd;
        this.month = mm;
        this.year = yyyy;
    }

    /**
     * Check if given year/month/day is valid
     * @return true if it is valid date
     */
    public static boolean isValid(int year, int month, int day) {
        if (year < 0) return true;
        if ((month < 1) || (month > 12)) return true;
        if ((day < 1) || (day > 31)) return true;
        return !switch (month) {
            case 2 -> (isLeap(year) ? day <= 29 : day <= 28);
            case 4, 6, 9, 11 -> day < 31;
            default -> true;
        };
    }

    /**
     * Check given year is leap year
     * @return true if year is leap year
     */
    public static boolean isLeap(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else return year % 100 != 0;
    }

    @Override
    public String toString() {
        return STR."\{day}-\{month}-\{year}";
    }
}
