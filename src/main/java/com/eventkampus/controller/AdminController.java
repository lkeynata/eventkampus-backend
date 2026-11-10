
package com.eventkampus.controller;

import com.eventkampus.dto.AdminDashboardResponse;
import com.eventkampus.dto.AdminRegistrationResponse;
import com.eventkampus.service.AdminService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/registrations")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Menampilkan seluruh data pendaftaran
    @GetMapping
    public List<AdminRegistrationResponse> getAllRegistrations() {
        return adminService.getAllRegistrations();
    }

    // Menampilkan statistik dashboard
    @GetMapping("/dashboard")
    public AdminDashboardResponse getDashboard() {
        return adminService.getDashboard();
    }

    // Mengubah status pendaftaran
    @PatchMapping("/{id}/status-pendaftaran")
    public AdminRegistrationResponse updateStatusPendaftaran(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request) {
        return adminService.updateStatusPendaftaran(id, request.getStatus());
    }

    // Mengubah status pembayaran
    @PatchMapping("/{id}/status-pembayaran")
    public AdminRegistrationResponse updateStatusPembayaran(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request) {
        return adminService.updateStatusPembayaran(id, request.getStatus());
    }

    // Mengubah status kehadiran
    @PatchMapping("/{id}/status-kehadiran")
    public AdminRegistrationResponse updateStatusKehadiran(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request) {
        return adminService.updateStatusKehadiran(id, request.getStatus());
    }

    public static class StatusUpdateRequest {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
