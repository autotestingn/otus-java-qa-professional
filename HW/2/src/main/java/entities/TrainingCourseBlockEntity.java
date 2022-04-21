package entities;

import java.time.LocalDate;

public class TrainingCourseBlockEntity {
    private String name;
    private Integer price;

    public TrainingCourseBlockEntity(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "TrainingCourseBlockEntity{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
