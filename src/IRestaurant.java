import java.util.List;

public interface IRestaurant extends Comparable<IRestaurant> {
    
    public String getName();

    public void setName(String name);

    public Address getAddress();

    public void setAddress(Address address);

    public double getLatitude();

    public void setLatitude(double latitude);

    public double getLongitude();

    public void setLongtitude(double longtitude);

    public double getStars();

    public void setStars(double stars);

    public List<String> getCategory();

    public void setCategory(List<String> category);
    
    public int compareTo(IRestaurant that);
    
    public Point getLocation();
}
