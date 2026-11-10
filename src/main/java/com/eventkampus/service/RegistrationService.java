
package com.eventkampus.service;

import com.eventkampus.dto.RegistrationRequest;
import com.eventkampus.entity.Event;
import com.eventkampus.entity.Participant;
import com.eventkampus.entity.Registration;
import com.eventkampus.repository.EventRepository;
import com.eventkampus.repository.ParticipantRepository;
import com.eventkampus.repository.RegistrationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationService(
            EventRepository eventRepository,
            ParticipantRepository participantRepository,
            RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional
    public Registration register(RegistrationRequest request) {

        // Cari dan kunci event
        Event event = eventRepository.findByIdForUpdate(request.getEventId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event tidak ditemukan"));

        // Pastikan event aktif
        if (!"Aktif".equalsIgnoreCase(event.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Event tidak aktif");
        }

        // Cari peserta berdasarkan email
        Participant participant = participantRepository
                .findByEmail(request.getEmail())
                .orElseGet(() -> participantRepository.save(
                        new Participant(
                                request.getNim(),
                                request.getNama(),
                                request.getEmail(),
                                request.getTelepon()
                        )
                ));

        // Cek pendaftaran ganda
        if (registrationRepository.existsByEvent_IdAndParticipant_Id(
                event.getId(), participant.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Peserta sudah terdaftar pada event ini");
        }

        // Cek kuota
        long jumlahPendaftar =
                registrationRepository.countByEvent_Id(event.getId());

        if (jumlahPendaftar >= event.getKuota()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Kuota event sudah penuh");
        }

        // Buat pendaftaran
        Registration registration = new Registration();
        registration.setParticipant(participant);
        registration.setEvent(event);
        registration.setTanggalDaftar(LocalDateTime.now());
        registration.setStatusPendaftaran("Menunggu Verifikasi");

        registration.setStatusPembayaran(
                event.getBiaya().signum() > 0 ? "Menunggu" : "Lunas"
        );

        registration.setStatusKehadiran("Tidak Hadir");

        return registrationRepository.save(registration);
    }
}
