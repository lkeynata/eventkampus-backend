package com.eventkampus.repository;

import com.eventkampus.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Optional<Participant> findByEmail(String email);

    long countByRegistrations_Event_Id(Long eventId);
}