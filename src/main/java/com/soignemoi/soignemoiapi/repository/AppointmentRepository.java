package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment save(Appointment appointment);
    Appointment findById(int id);
    List<Appointment> findAllByDoctorId(int doctorId);
    List<Appointment> findAllByVisitorId(int visitorId);
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Appointment a " +
            "WHERE a.visitor.id = :visitorId " +
            "AND (:date_start BETWEEN a.dateStart AND a.dateEnd " +
            "OR :date_end BETWEEN a.dateStart AND a.dateEnd)")
    boolean doesAppointmentExist(
            @Param("date_start") Date startDate,
            @Param("date_end") Date endDate,
            @Param("visitorId") int visitorId);

    @Query("SELECT a " +
            "FROM Appointment a " +
            "WHERE a.visitor.id = :visitorId " +
            "AND (:date_start BETWEEN a.dateStart AND a.dateEnd " +
            "OR :date_end BETWEEN a.dateStart AND a.dateEnd)")
    Optional<Appointment> findAppointmentByDateAndVisitorId(
            @Param("date_start") Date startDate,
            @Param("date_end") Date endDate,
            @Param("visitorId") int visitorId);
}
