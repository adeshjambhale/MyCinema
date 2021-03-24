package in.edu.vpt.mycinema;

public class FeedBacks {
   private   String rating1;
   private String rating2;
   private String rating3;

    public FeedBacks() {
    }

    public FeedBacks(String rating1, String rating2, String rating3) {
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;

    }

    public String getRating1() {
        return rating1;
    }

    public void setRating1(String rating1) {
        this.rating1 = rating1;
    }

    public String getRating2() {
        return rating2;
    }

    public void setRating2(String rating2) {
        this.rating2 = rating2;
    }

    public String getRating3() {
        return rating3;
    }

    public void setRating3(String rating3) {
        this.rating3 = rating3;
    }
}
