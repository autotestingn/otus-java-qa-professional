package pages;

public class TrainingCourseBlock {
    private String name;
    private Integer price;

    public TrainingCourseBlock(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TrainingCourseBlock{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
