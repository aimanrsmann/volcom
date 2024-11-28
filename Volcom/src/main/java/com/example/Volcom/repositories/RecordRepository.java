package com.example.Volcom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Volcom.models.Record;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(String title, String artist);
}
