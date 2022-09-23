package co.edu.uniandes.dse.parcialejemplo.services;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.*;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HabitacionService {

    @Autowired 
    HabitacionRepository habitacionRepository;

    @Autowired 
    HotelRepository hotelRepository;

    public HabitacionEntity createHabitacion(Long hotelId, HabitacionEntity habitacionEntity ) throws EntityNotFoundException, IllegalOperationException{
        log.info("Inicia proceso de creacion de habitacion");
        Optional<HotelEntity> hotelEntity = hotelRepository.findById(hotelId);
        if (hotelEntity.isEmpty())
            throw new EntityNotFoundException("Hotel no encontrado");

        if(habitacionEntity.getIdentificacion() > habitacionEntity.getNum_camas())
            throw new IllegalOperationException("El numero de identificion es mayor al numero de camas");
        
        log.info("Termina el proceso de creacion de habitacion");
        return habitacionRepository.save(habitacionEntity);
    }
}
