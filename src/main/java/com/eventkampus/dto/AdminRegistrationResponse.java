package com.eventkampus.dto;

import com.eventkampus.entity.Registration;
import java.time.LocalDateTime;

public class AdminRegistrationResponse {

    private Long id;
    private Long eventId;
    private String namaEvent;
    private Long participantId;
    private String nim;
    private String namaPeserta;
    private String email;
    private String telepon;
    private LocalDateTime tanggalDaftar;
    private String statusPendaftaran;
    private String statusPembayaran;
    private String statusKehadiran;

    public AdminRegistrationResponse(Registration registration) {
        this.id = registration.getId();
        this.eventId = registration.getEvent().getId();
        this.namaEvent = registration.getEvent().getNamaEvent();
        this.participantId = registration.getParticipant().getId();
        this.nim = registration.getParticipant().getNim();
        this.namaPeserta = registration.getParticipant().getNama();
        this.email = registration.getParticipant().getEmail();
        this.telepon = registration.getParticipant().getTelepon();
        this.tanggalDaftar = registration.getTanggalDaftar();
        this.statusPendaftaran = registration.getStatusPendaftaran();
        this.statusPembayaran = registration.getStatusPembayaran();
        this.statusKehadiran = registration.getStatusKehadiran();
    }

    public Long getId() { return id; }
    public Long getEventId() { return eventId; }
    public String getNamaEvent() { return namaEvent; }
    public Long getParticipantId() { return participantId; }
    public String getNim() { return nim; }
    public String getNamaPeserta() { return namaPeserta; }
    public String getEmail() { return email; }
    public String getTelepon() { return telepon; }
    public LocalDateTime getTanggalDaftar() { return tanggalDaftar; }
    public String getStatusPendaftaran() { return statusPendaftaran; }
    public String getStatusPembayaran() { return statusPembayaran; }
    public String getStatusKehadiran() { return statusKehadiran; }
}