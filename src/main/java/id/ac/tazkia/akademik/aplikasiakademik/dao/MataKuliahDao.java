package id.ac.tazkia.akademik.aplikasiakademik.dao;

import id.ac.tazkia.akademik.aplikasiakademik.entity.Matakuliah;
import id.ac.tazkia.akademik.aplikasiakademik.entity.StatusRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MataKuliahDao extends PagingAndSortingRepository<Matakuliah, String> {
    Page<Matakuliah> findByStatusNotIn(StatusRecord status, Pageable page);

    List<Matakuliah> findByStatusNotIn(StatusRecord statusRecord);
}
