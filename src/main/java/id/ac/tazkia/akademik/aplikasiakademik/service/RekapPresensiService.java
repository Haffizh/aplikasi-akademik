package id.ac.tazkia.akademik.aplikasiakademik.service;

import id.ac.tazkia.akademik.aplikasiakademik.dao.RekapKehadiranMahasiswaDao;
import id.ac.tazkia.akademik.aplikasiakademik.dao.SesiKuliahDao;
import id.ac.tazkia.akademik.aplikasiakademik.entity.PresensiMahasiswa;
import id.ac.tazkia.akademik.aplikasiakademik.entity.RekapKehadiranMahasiswa;
import id.ac.tazkia.akademik.aplikasiakademik.entity.SesiKuliah;
import id.ac.tazkia.akademik.aplikasiakademik.entity.StatusPresensi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
public class RekapPresensiService {
    @Autowired private SesiKuliahDao sesiKuliahDao;
    @Autowired private RekapKehadiranMahasiswaDao rekapKehadiranMahasiswaDao;

    // tiap jam 1 malam
    @Scheduled(cron = "* 59 13 * * *")
    public void isiRekap(){
        isiRekap(LocalDate.now().minusDays(1));
    }

    public void isiRekap(LocalDate tanggal) {
        LocalDateTime jam00 = tanggal.atTime(0,0,0,0);
        LocalDateTime jam00besoknya = jam00.plusDays(1);
        Iterable<SesiKuliah> kuliahKemarin = sesiKuliahDao.cariSesiKuliah(jam00, jam00besoknya);
        for(SesiKuliah s : kuliahKemarin) {
            for(PresensiMahasiswa p : s.getDaftarPresensiMahasiswa()){
                RekapKehadiranMahasiswa r = rekapKehadiranMahasiswaDao.findByMahasiswaAndJadwal(p.getMahasiswa(), s.getJadwal());
                if(r == null) {
                    r = new RekapKehadiranMahasiswa();
                    r.setMahasiswa(p.getMahasiswa());
                    r.setJadwal(s.getJadwal());
                }
                if(StatusPresensi.HADIR.equals(p.getStatusPresensi())){
                    r.setHadir(r.getHadir() + 1);
                }
                if(StatusPresensi.MANGKIR.equals(p.getStatusPresensi())){
                    r.setHadir(r.getMangkir() + 1);
                }
                if(StatusPresensi.IZIN.equals(p.getStatusPresensi())){
                    r.setHadir(r.getIzin() + 1);
                }
                if(StatusPresensi.SAKIT.equals(p.getStatusPresensi())){
                    r.setHadir(r.getSakit() + 1);
                }
                if(StatusPresensi.TERLAMBAT.equals(p.getStatusPresensi())){
                    r.setHadir(r.getTerlambat() + 1);
                }
                r.setTerakhirUpdate(LocalDateTime.now());
                rekapKehadiranMahasiswaDao.save(r);
            }
        }
    }
}
