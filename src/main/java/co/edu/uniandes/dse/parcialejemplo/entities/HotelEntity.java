package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;

@Getter
@Setter
@Entity
public class HotelEntity extends BaseEntity{

    private String nombre;
    private String direccion;
    private Float estrellas; 

    @PodamExclude
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<HabitacionEntity> habitaciones = new ArrayList<>();

}
