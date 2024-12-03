package expeditors.week03.solutions.task01;

/**
 * Class used to track multiple elements of a student's
 * grade.
 */
public final class GradeDetail {
    private final int index;
    private final String studentName;
    private final int grade;
    private final String letterGrade;

    public GradeDetail(int index, String studentName, int grade, String letterGrade) {
        this.index = index;
        this.studentName = studentName;
        this.grade = grade;
        this.letterGrade = letterGrade;
    }

    public int index() {
        return index;
    }

    public String studentName() {
        return studentName;
    }

    public int grade() {
        return grade;
    }

    public String letterGrade() {
        return letterGrade;
    }

    @Override
    public String toString() {
        return String.format("%s had a score of %d (%s)", studentName(),
                grade(), letterGrade());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GradeDetail gradeDetail = (GradeDetail) obj;
        return index == gradeDetail.index && studentName.equals(gradeDetail.studentName) &&
                grade == gradeDetail.grade && letterGrade.equals(gradeDetail.letterGrade);
    }

    @Override
    public int hashCode() {
        return index ^ (studentName == null ? "".hashCode() : studentName.hashCode()) ^
                grade ^ (letterGrade == null ? "".hashCode() : letterGrade.hashCode());
    }
}
