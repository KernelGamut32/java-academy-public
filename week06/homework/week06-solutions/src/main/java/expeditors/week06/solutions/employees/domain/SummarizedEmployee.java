package expeditors.week06.solutions.employees.domain;

public class SummarizedEmployee {

    private final String name;
    private final String jobTitle;

    public SummarizedEmployee(String name, String jobTitle) {
        this.name = name;
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return String.format("Summary: %s (%s)", name, jobTitle);
    }

}
