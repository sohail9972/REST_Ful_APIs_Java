package testing.ReaptingActions.Pojos;

public class Booking {

//    While Declaring the Variables must Macth the Values of Given Payload Else the
//    Test Cases Will be failing
    private String firstname;
    private String lastname;
    private String additionalneeds;
    private int totalprice;
    private boolean depositpaid;

    private BookingDates bookingdates;

    public Booking(){

    }
    public Booking(String fname,String lname,String aneeds,
                   int tprice,boolean dpaid,BookingDates dates){
        setFirstname(fname);
        setLastname(lname);
        setAdditionalneeds(aneeds);
        setTotalprice(tprice);
        setDepositpaid(dpaid);
        setBookingdates(dates);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastnaem) {
        this.lastname = lastnaem;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

}
