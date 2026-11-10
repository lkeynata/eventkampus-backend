
package com.eventkampus.service;

import com.eventkampus.dto.AdminDashboardResponse;
import com.eventkampus.dto.AdminRegistrationResponse;
import com.eventkampus.entity.Registration;
import com.eventkampus.repository.RegistrationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdminService {

    private final RegistrationRepository registrationRepository;

    public AdminService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // Menampilkan seluruh data pendaftaran
    @Transactional(readOnly = true)
    public List<AdminRegistrationResponse> getAllRegistrations() {
        return registrationRepository.findAll()
                .stream()
                .map(AdminRegistrationResponse::new)
                .toList();
    }

    // Menampilkan statistik dashboard admin
    @Transactional(readOnly = true)
    public AdminDashboardResponse getDashboard() {

        long totalPendaftaran = registrationRepository.count();

        long menungguVerifikasi =
                registrationRepository.countByStatusPendaftaran("Menunggu Verifikasi");

        long pembayaranLunas =
                registrationRepository.countByStatusPembayaran("Lunas");

        long pesertaHadir =
                registrationRepository.countByStatusKehadiran("Hadir");

        return new AdminDashboardResponse(
                totalPendaftaran,
                menungguVerifikasi,
                pembayaranLunas,
                pesertaHadir
        );
    }

    // Mengubah status pendaftaran
    @Transactional
    public AdminRegistrationResponse updateStatusPendaftaran(Long id, String status) {

        if (!List.of("Menunggu Verifikasi", "Terverifikasi").contains(status)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Status pendaftaran tidak valid");
        }

        Registration registration = getRegistration(id);
        registration.setStatusPendaftaran(status);

        return new AdminRegistrationResponse(
                registrationRepository.save(registration));
    }

    // Mengubah status pembayaran
    @Transactional
    public AdminRegistrationResponse updateStatusPembayaran(Long id, String status) {

        if (!List.of("Menunggu", "Lunas", "Tidak Valid").contains(status)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Status pembayaran tidak valid");
        }

        Registration registration = getRegistration(id);
        registration.setStatusPembayaran(status);

        return new AdminRegistrationResponse(
                registrationRepository.save(registration));
    }

    // Mengubah status kehadiran
    @Transactional
    public AdminRegistrationResponse updateStatusKehadiran(Long id, String status) {

        if (!List.of("Hadir", "Tidak Hadir").contains(status)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Status kehadiran tidak valid");
        }

        Registration registration = getRegistration(id);
        registration.setStatusKehadiran(status);

        return new AdminRegistrationResponse(
                registrationRepository.save(registration));
    }

    private Registration getRegistration(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Data pendaftaran tidak ditemukan"));
    }
}
