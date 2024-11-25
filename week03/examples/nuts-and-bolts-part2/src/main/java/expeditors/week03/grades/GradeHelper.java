package expeditors.week03.grades;

public class GradeHelper {
    public String caculateGradeLetter(Integer gradeValue) {
        return switch (gradeValue) {
            case Integer g when gradeValue >= 90 -> "A";
            case Integer g when gradeValue >= 80 -> "B";
            case Integer g when gradeValue >= 70 -> "C";
            case Integer g when gradeValue >= 60 -> "D";
            default -> "F";
        };
    }
}
