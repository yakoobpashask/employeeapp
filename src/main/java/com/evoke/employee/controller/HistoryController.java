package com.evoke.employee.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.evoke.employee.entities.HrmsHistory;
import com.evoke.employee.exeception.ResourceNotFoundException;
import com.evoke.employee.service.HrmsHistoryService;

@RestController
@RequestMapping("/history")
public class HistoryController {


    @Autowired
    HrmsHistoryService historyService;

    @RequestMapping("/getAll")
    public List<HrmsHistory> getAllHrmsHistory() throws ResourceNotFoundException {
        List<HrmsHistory> historyList = new ArrayList<>();
        historyService.getAllHrmsHistory()
                .forEach(historyList::add);
        if (historyList.size() == 0) {
            throw new ResourceNotFoundException("History Record is Not Found..");
        }

        return historyList;
    }

    @GetMapping("/get/{id}")
    public HrmsHistory findById(@PathVariable Integer id) throws ResourceNotFoundException {

        HrmsHistory history = historyService.findById(id);
        if (history == null) {
            throw new ResourceNotFoundException("History Not Found..");
        }
        return history;
    }
}
