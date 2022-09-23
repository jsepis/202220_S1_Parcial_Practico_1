package co.edu.uniandes.dse.parcialejemplo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.HotelEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.HotelRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Transactional
    public HotelEntity createHotel(HotelEntity hotelEntity) throws  IllegalOperationException{
        log.info("Comienza el proceso de creacion de hotel");
        if (hotelEntity.getEstrellas()< 1 || hotelEntity.getEstrellas() >5){
            throw new IllegalOperationException("El numero de estrellas no es valido");
        }

        return hotelRepository.save(hotelEntity);
    }
        

}
