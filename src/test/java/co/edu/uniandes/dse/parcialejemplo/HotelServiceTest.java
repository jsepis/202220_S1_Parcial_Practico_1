package co.edu.uniandes.dse.parcialejemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.event.spi.EntityCopyObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.services.HotelService;
import co.edu.uniandes.dse.parcialejemplo.entities.*;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(HotelService.class)

public class HotelServiceTest {
    
    @Autowired
    private HotelService hotelService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<HotelEntity> hotelList = new ArrayList<>();

    @BeforeEach
        void setUp() {
                clearData();
                insertData();
        }
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from HotelEntity");
    }

    private void insertData(){
        for (int i = 0; i < 3; i++){
            HotelEntity hotelEntity = factory.manufacturePojo(HotelEntity.class);
            entityManager.persist(hotelEntity);
            hotelList.add(hotelEntity);
        }
        
    @Test
    void testCreateHoteles() throws IllegalOperationException {
        HotelEntity newEntity = factory.manufacturePojo(HotelEntity.class);
        newEntity.setNombre("Las acacias");
        HotelEntity result = hotelService.createHotel(newEntity);
        assertNotNull(result);
        HotelEntity entity = entityManager.find(HotelEntity.class, result.getId());
        assertEquals(newEntity.getNombre(), entity.getNombre());

        
    }
}
