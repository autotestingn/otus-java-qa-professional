package pages;

import java.time.LocalDate;

public class CourseBlock {
    private String name;
    private LocalDate startDate;

    public CourseBlock(String name, LocalDate startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "CourseBlock{" +
                "name='" + name + '\'' +
                ", startDate=" + startDate +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public static CourseBlock max(CourseBlock x, CourseBlock y) {
        return x.getStartDate().isAfter(y.getStartDate()) ? x : y;
    }

    public static CourseBlock min(CourseBlock x, CourseBlock y) {
        return x.getStartDate().isBefore(y.getStartDate()) ? x : y;
    }
}
