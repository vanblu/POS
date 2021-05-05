
public class Restaurant implements IRestaurant {
    String name;
    Address address;
    String latitude;
    String longtitude;
    String cusineType;
    double stars;
    String category;
    
    

    public Restaurant() {

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getCusineType() {
        return cusineType;
    }

    public void setCusineType(String cusineType) {
        this.cusineType = cusineType;
    }

    public double getStars() {
        return this.stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
        return (int) (this.getStars() * 100 - that.getStars() * 100);
    }
}
