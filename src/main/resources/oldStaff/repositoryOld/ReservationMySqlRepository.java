/*
package repositoryOld;

import com.hotel.domain.Reservation;
import com.hotel.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;



    @Repository
    public class ReservationMySqlRepository implements ReservationRepository {

        @Autowired
        private EntityManager entityManager;

        @Override
        public List<Reservation> findAll() {
            TypedQuery<Reservation> query = entityManager.createQuery("SELECT r FROM Reservation r", Reservation.class);
            return query.getResultList();
        }

        @Override
        public Reservation findById(Long id) {
            Reservation reservation = entityManager.find(Reservation.class, id);
            if (reservation == null) {
                throw new ResourceNotFoundException("Reservation", "id", id);
            }
            return reservation;
        }

        @Override
        public Reservation save(Reservation reservation) {
            entityManager.persist(reservation);
            return reservation;
        }

        @Override
        public Reservation update(Long id, Reservation reservationDetails) {
            Reservation reservation = entityManager.find(Reservation.class, id);
            if (reservation == null) {
                throw new ResourceNotFoundException("Reservation", "id", id);
            }
            reservation.setStartDate(reservationDetails.getStartDate());
            reservation.setEndDate(reservationDetails.getEndDate());
            reservation.setRoom(reservationDetails.getRoom());
            reservation.setGuests(reservationDetails.getGuests());
            entityManager.merge(reservation);
            return reservation;
        }


        @Override
        public void delete(Reservation reservation) {
            //Reservation reservation = entityManager.find(Reservation.class, reservation);
            if (reservation == null) {
                throw new ResourceNotFoundException("Reservation", "id", reservation);
            }
            entityManager.remove(reservation);
        }
    }
*/
