package gts.spring.config;

import org.springframework.stereotype.Component;

@Component
public class SpecialistHealthCareProvider extends StandardHealthCareProvider implements HealthCareProvider {

    private String specialty;

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    @Override
    public void setYearsOfExperience(int yearsOfExperience) {
        super.setYearsOfExperience(yearsOfExperience);
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return String.format("%s - Specialty: %s",
                super.toString(), getSpecialty());
    }
}
