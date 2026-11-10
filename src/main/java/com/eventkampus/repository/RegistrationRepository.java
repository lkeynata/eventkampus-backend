
package com.eventkampus.repository;

import com.eventkampus.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    boolean existsByEvent_IdAndParticipant_Id(Long eventId, Long participantId);

    long countByEvent_Id(Long eventId);

    long countByParticipant_Id(Long participantId);

    long countByStatusPendaftaran(String statusPendaftaran);

    long countByStatusPembayaran(String statusPembayaran);

    long countByStatusKehadiran(String statusKehadiran);
}
