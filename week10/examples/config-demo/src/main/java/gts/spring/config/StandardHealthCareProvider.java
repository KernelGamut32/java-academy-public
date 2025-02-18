package gts.spring.config;

import org.springframework.stereotype.Component;

@Component
public class StandardHealthCareProvider implements HealthCareProvider {

    private String name;
    private String address;
    private int yearsOfExperience;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return String.format("%s at %s (with %d years of experience)",
                getName(), getAddress(), getYearsOfExperience());
    }
}
