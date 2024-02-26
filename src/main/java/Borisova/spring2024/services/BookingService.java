package Borisova.spring2024.services;

import Borisova.spring2024.domain.Table;

import java.util.List;

public interface BookingService {
    public List<Table> getFreeTable();
}
