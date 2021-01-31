package dto;

public class StationDescription {
    private int number;
    private double latitude;
    private double longitude;
    private String name;

    public StationDescription() {
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}
