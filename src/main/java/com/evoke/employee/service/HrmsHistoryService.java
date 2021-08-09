package com.evoke.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.evoke.employee.domain.Event;
import com.evoke.employee.domain.Operation;
import com.evoke.employee.entities.HrmsHistory;
import com.evoke.employee.repository.HrmsHistoryRepository;

@Service
public class HrmsHistoryService {


    @Autowired
    HrmsHistoryRepository historyRepo;



    public List<HrmsHistory> getAllHrmsHistory() {

        List<HrmsHistory> listHistory = new ArrayList<>();
        historyRepo.findAll()
                .forEach(listHistory::add);
        return listHistory;

    }

    public HrmsHistory findById(Integer id) {

        Optional<HrmsHistory> optHistory = historyRepo.findById(id);
        HrmsHistory history = null;
        if (optHistory.isPresent()) {
            history = optHistory.get();
        }
        return history;
    }

    public void insertHistory(Event event, Operation operation, Integer id) {


        HrmsHistory history = new HrmsHistory();
        history.setEvent(operation + "_" + event + "_" + id);
        history.setComments(event + " " + operation);
        historyRepo.save(history);
    }
}
