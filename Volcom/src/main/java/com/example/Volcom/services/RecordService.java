package com.example.Volcom.services;

import com.example.Volcom.models.Record;
import com.example.Volcom.repositories.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public Record getRecordById(int id) {
        return recordRepository.findById(id).orElse(null);
    }

    public void saveRecord(Record record) {
        recordRepository.save(record);
    }

    public void deleteRecordById(int id) {
        recordRepository.deleteById(id);
    }

    public List<Record> searchRecords(String keyword) {
        return recordRepository.findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(keyword, keyword);
    }

    // Method to calculate total price of selected records
    public double calculateTotalPrice(List<Integer> selectedRecordIds) {
        double total = 0.0;

        // Loop through the selected record IDs and add their prices
        for (Integer recordId : selectedRecordIds) {
            Record record = getRecordById(recordId);  // Retrieve the record by its ID
            if (record != null) {
                total += record.getPrice();  // Add the price to the total
            }
        }

        return total;
    }
}
