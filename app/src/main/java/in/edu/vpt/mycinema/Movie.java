package in.edu.vpt.mycinema;

public class Movie {
   private String Seats;
   private String time;

    public  Movie() {
    }

    public Movie(String seats, String time) {
        Seats = seats;
        this.time = time;
    }

    public String getSeats() {
        return Seats;
    }

    public void setSeats(String seats) {
        Seats = seats;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
