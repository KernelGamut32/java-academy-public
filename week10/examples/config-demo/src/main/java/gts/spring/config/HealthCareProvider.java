package gts.spring.config;

public interface HealthCareProvider {

    String getName();
    void setName(String name);
    String getAddress();
    void setAddress(String address);
    int getYearsOfExperience();
    void setYearsOfExperience(int yearsOfExperience);
}
