
package com.eventkampus.service;

import com.eventkampus.entity.Participant;
import com.eventkampus.repository.ParticipantRepository;
import com.eventkampus.repository.RegistrationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final RegistrationRepository registrationRepository;

    public ParticipantService(
            ParticipantRepository participantRepository,
            RegistrationRepository registrationRepository) {
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
    }

    @Transactional(readOnly = true)
    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    @Transactional
    public Participant updateParticipant(Long id, Participant request) {
        Participant participant = getParticipant(id);

        participant.setNim(request.getNim());
        participant.setNama(request.getNama());
        participant.setEmail(request.getEmail());
        participant.setTelepon(request.getTelepon());

        return participantRepository.save(participant);
    }

    @Transactional
    public void deleteParticipant(Long id) {
        Participant participant = getParticipant(id);

        if (registrationRepository.countByParticipant_Id(id) > 0) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Peserta masih memiliki riwayat pendaftaran"
            );
        }

        participantRepository.delete(participant);
    }

    private Participant getParticipant(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Peserta tidak ditemukan"
                ));
    }
}
