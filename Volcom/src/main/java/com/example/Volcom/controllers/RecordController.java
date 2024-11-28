package com.example.Volcom.controllers;

import com.example.Volcom.models.Record;
import com.example.Volcom.services.RecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    // Display Add Record Page
    @GetMapping("/add")
    public String addRecordPage() {
        return "records/add-record";
    }

    // Handle Add Record Form Submission
    @PostMapping("/add")
    public String addRecord(@ModelAttribute Record record) {
        // Saving the new record
        recordService.saveRecord(record);
        return "redirect:/records/index"; // Redirecting to index page after saving
    }

    // View the details of a specific record
    @GetMapping("/view/{id}")
    public String viewRecord(@PathVariable int id, Model model) {
        Record record = recordService.getRecordById(id);
        if (record == null) {
            return "redirect:/records/index"; // Redirect if record is not found
        }
        model.addAttribute("record", record);
        return "records/view-record"; // Returns the view-record.html page
    }

    // Display Update Record Page
    @GetMapping("/update/{id}")
    public String updateRecordPage(@PathVariable int id, Model model) {
        Record record = recordService.getRecordById(id);
        if (record == null) {
            return "redirect:/index"; // Redirect if record not found
        }
        model.addAttribute("record", record); // Add record to the model
        return "records/update-record"; // Display the update-record.html page
    }

    // Handle Update Record Form Submission
    @PostMapping("/update/{id}")
    public String updateRecord(@PathVariable int id, @ModelAttribute Record record) {
        record.setId(id);  // Ensure the ID remains the same
        recordService.saveRecord(record);
        return "redirect:/index";  // Redirect after successful update
    }


    // Handle Deleting a Record
    @GetMapping("/delete/{id}")
    public String deleteRecord(@PathVariable int id) {
        recordService.deleteRecordById(id);
        return "redirect:/records/index"; // Redirect to index after deletion
    }

    // Display all records (index page)
    @GetMapping("/index")
    public String viewAllRecords(Model model) {
        model.addAttribute("records", recordService.getAllRecords());
        return "records/index"; // Returns the index.html page with all records
    }

    // Search records by keyword
    @GetMapping("/search")
    public String searchRecords(@RequestParam("keyword") String keyword, Model model) {
        // Search for records by title or artist
        List<Record> records = recordService.searchRecords(keyword);
        model.addAttribute("records", records); // Pass the filtered records to the template
        return "records/index"; // Return the same index page with updated records
    }

    // Handle total price calculation for selected records
    @PostMapping("/total")
    public String calculateTotal(@RequestParam("selectedRecords") List<Integer> selectedRecords, Model model) {
        double totalPrice = recordService.calculateTotalPrice(selectedRecords);  // Use service method for calculation

        // Add the total price to the model
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("selectedRecords", selectedRecords);

        // Return the view with the total price
        return "records/total-price";  // Create a new view to display the total price
    }
}
