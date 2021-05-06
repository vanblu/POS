
public interface IRestaurant extends Comparable<IRestaurant> {
    
    public String getName();

    public void setName(String name);

    public Address getAddress();

    public void setAddress(Address address);

    public String getLatitude();

    public void setLatitude(String latitude);

    public String getLongitude();

    public void setLongtitude(String longtitude);

    public String getCusineType();

    public void setCusineType(String cusineType);

    public double getStars();

    public void setStars(double stars);

    public String getCategory();

    public void setCategory(String category);
    
    public int compareTo(IRestaurant that);
    
    public Point getLocation();
}
