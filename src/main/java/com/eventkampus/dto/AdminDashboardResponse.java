
package com.eventkampus.dto;

public class AdminDashboardResponse {

    private long totalPendaftaran;
    private long menungguVerifikasi;
    private long pembayaranLunas;
    private long pesertaHadir;

    public AdminDashboardResponse(
            long totalPendaftaran,
            long menungguVerifikasi,
            long pembayaranLunas,
            long pesertaHadir) {
        this.totalPendaftaran = totalPendaftaran;
        this.menungguVerifikasi = menungguVerifikasi;
        this.pembayaranLunas = pembayaranLunas;
        this.pesertaHadir = pesertaHadir;
    }

    public long getTotalPendaftaran() {
        return totalPendaftaran;
    }

    public long getMenungguVerifikasi() {
        return menungguVerifikasi;
    }

    public long getPembayaranLunas() {
        return pembayaranLunas;
    }

    public long getPesertaHadir() {
        return pesertaHadir;
    }
}
