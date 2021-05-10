import java.util.List;

public class Restaurant implements IRestaurant {
    String name;
    Address address;
    double latitude;
    double longtitude;
   
    double stars;
    List<String> category;
    
    

    public Restaurant() {

    }
    
    public Restaurant(double stars) {
        this.stars = stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }



    public double getStars() {
        return this.stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
    
    public Point getLocation() {
        return Coordinates.latLongToPoint(this.getLatitude(), 
               this.getLongitude());
    }
    
    public String toString() {
        return this.name; 
    }

    @Override
    // natural order - order by Stars
    public int compareTo(IRestaurant that) {
        if (this.equals(that)) {
            return 0;
        }
        if (this.getStars() == 0 || that.getStars() == 0) {
            return -1; // randomly order if no stars given 
        }
        if (Math.abs(this.getStars() - that.getStars()) < 0.05) {
            return 1; // randomly order if same stars
        }

        return (int) (this.getStars() * 100 - that.getStars() * 100);
    }
}


