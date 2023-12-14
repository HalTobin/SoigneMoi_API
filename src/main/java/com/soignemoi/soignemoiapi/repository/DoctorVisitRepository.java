package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.DoctorVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DoctorVisitRepository extends JpaRepository<DoctorVisit, Integer> {

    DoctorVisit save(DoctorVisit doctorVisit);

    Optional<DoctorVisit> findById(int id);

    List<DoctorVisit> findByDoctorId(int doctorId);

    @Query("SELECT COUNT(dv) > 0 FROM DoctorVisit dv WHERE dv.appointment.id = :appointmentId AND dv.date = CURRENT_DATE")
    boolean existsByAppointmentIdAndDate(
            @Param("appointmentId") int appointmentId);

    @Query("SELECT COUNT(dv) FROM DoctorVisit dv WHERE dv.doctor.id = :doctorId AND dv.date = CURRENT_DATE")
    int howMuchVisitForTodayByDoctorId(@Param("doctorId") int doctorId);

    @Query("SELECT dv FROM DoctorVisit dv WHERE dv.appointment.id = :appointmentId ORDER BY dv.date DESC LIMIT 1")
    Optional<DoctorVisit> findLatestByAppointmentId(@Param("appointmentId") int appointmentId);

    @Modifying
    @Query("UPDATE DoctorVisit visit SET visit.done = :done WHERE visit.id = :id")
    void setDoneForDoctorVisit(@Param("done") boolean done, @Param("id") int id);

    @Query("SELECT dv " +
            "FROM DoctorVisit dv " +
            "WHERE dv.doctor.id = :doctorId AND dv.date = CURRENT_DATE")
    List<DoctorVisit> findForTodayByDoctorId(@Param("doctorId") int doctorId);

}
