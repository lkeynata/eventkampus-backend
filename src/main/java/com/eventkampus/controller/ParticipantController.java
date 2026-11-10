package com.eventkampus.controller;

import com.eventkampus.entity.Participant;
import com.eventkampus.service.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    // Menampilkan seluruh peserta
    @GetMapping
    public List<Participant> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    // Mengubah data peserta
    @PutMapping("/{id}")
    public Participant updateParticipant(
            @PathVariable Long id,
            @RequestBody Participant request) {
        return participantService.updateParticipant(id, request);
    }

    // Menghapus peserta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}
