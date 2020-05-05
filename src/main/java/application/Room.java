package application;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String countryName;

    @NotNull
    private boolean isOn;

    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public void initFromTemplate(Room template) {
        this.id = template.getId();
        this.name = template.getName();
        this.countryName = template.getCountryName();
        this.createdAt = template.getCreatedAt();
        this.isOn = template.isOn();
    }


}
