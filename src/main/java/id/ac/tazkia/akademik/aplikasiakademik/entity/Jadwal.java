package id.ac.tazkia.akademik.aplikasiakademik.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Data
public class Jadwal {

    @Id
    @GeneratedValue(generator = "uuid" )
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_matakuliah_kurikulum")
    private MatakuliahKurikulum matakuliahKurikulum;

    @ManyToOne
    @JoinColumn(name = "id_hari")
    private Hari idHari;

    @Column(columnDefinition = "TIME")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jamMulai;

    @Column(columnDefinition = "TIME")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jamSelesai;

    @ManyToOne
    @JoinColumn(name = "id_tahun_akademik")
    private TahunAkademik tahunAkademik;

    @ManyToOne
    @JoinColumn(name = "id_tahun_akademik_prodi")
    private TahunAkademikProdi tahunAkademikProdi;

    @ManyToOne
    @JoinColumn(name = "id_prodi")
    private Prodi prodi;

    @ManyToOne
    @JoinColumn(name = "id_dosen_pengampu")
    private Dosen dosen;

    private BigDecimal bobotUts;

    private BigDecimal bobotUas;

    private BigDecimal bobotTugas;

    private BigDecimal bobotPresensi;

    @ManyToOne
    @JoinColumn(name = "id_ruangan")
    private Ruangan ruangan;

    @ManyToOne
    @JoinColumn(name = "id_kelas")
    private Kelas idKelas;

    private Integer jumlahSesi;

    private String  finalStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusRecord status = StatusRecord.AKTIF;

    @ManyToOne
    @JoinColumn(name = "id_program")
    private Program program;

}
