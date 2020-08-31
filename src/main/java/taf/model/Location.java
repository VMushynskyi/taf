package taf.model;

import java.util.Objects;

public class Location {

    private Long locationId;
    private String locationName;
    private String address;

    public Location() {
    }

    public Location(Long locationId, String locationName, String address) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.address = address;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(locationId, location.locationId) &&
                Objects.equals(locationName, location.locationName) &&
                Objects.equals(address, location.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, locationName, address);
    }
}
