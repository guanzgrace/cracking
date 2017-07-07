import java.util.Date;
// Calculates the days from 0 for each date which allows for
// calculation between any two dates.
public class DateUtil {
  private static final int[] REG_YEAR = {0, 31, 59, 90, 120, 151, 181, 
    212, 243, 273, 304, 334};
  private static final int[] LEAP_YEAR = {0, 31, 60, 91, 121, 152, 182,
    213, 244, 274, 305, 335};
  
  public static int daysInBetween(Date a, Date b){
    return Math.abs(DateUtil.daysFromZero(a) - DateUtil.daysFromZero(b));
  }
  private static int daysFromZero(Date a) {
    int year = a.getYear();
    int month = a.getMonth();
    int day = a.getDay();
    int datesTilMonth = REG_YEAR[month - 1];
    if (year % 4 == 0) datesTilMonth = LEAP_YEAR[month - 1];
    int datesTilYear = ((year / 4) + (year / 100)) * 366
      + (year - (year / 4) - (year / 100)) * 365;
    return datesTilYear + datesTilMonth + day;
  }
}
