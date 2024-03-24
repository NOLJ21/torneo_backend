package ucb.validador.backend.dto;

public class PositionDto {
    private Integer id;
    private String name;

    public PositionDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PositionDto [id=" + id + ", name=" + name + "]";
    }
}
