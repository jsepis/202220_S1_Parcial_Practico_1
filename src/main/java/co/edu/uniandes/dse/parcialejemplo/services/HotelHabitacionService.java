package co.edu.uniandes.dse.parcialejemplo.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.*;

import co.edu.uniandes.dse.parcialejemplo.repositories.HabitacionRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelHabitacionService {
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Transactional
    public HabitacionEntity addHabitacion(Long hotelId, Long habitacionId) throws EntityNotFoundException {
        log.info("Inicia el proceso de anadirle una habitacion a el hotel con id = {0}", hotelId);

        Optional<HotelEntity> hotelEntity = hotelRepository.findById(hotelId);
        if (hotelEntity.isEmpty())
            throw new EntityNotFoundException("Hotel no encontrado");

        Optional<HabitacionEntity> habitacionEntity = habitacionRepository.findById(habitacionId);
        if (habitacionEntity.isEmpty())
            throw new EntityNotFoundException("Habitacion no encontrada");

        habitacionEntity.get().setHotel(hotelEntity.get());
        log.info("Termina el proceso de anadirle una habitacion a el hotel con id = {0}", hotelId);
		return habitacionEntity.get();
	}
}
