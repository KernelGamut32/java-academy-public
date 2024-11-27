package expeditors.week03.grades;

public class GradeHelper {
    public String caculateGradeLetter(Integer gradeValue) {
        return switch (gradeValue) {
            case Integer g when g >= 90 -> "A";
            case Integer g when g >= 80 -> "B";
            case Integer g when g >= 70 -> "C";
            case Integer g when g >= 60 -> "D";
            default -> "F";
        };
    }
}
