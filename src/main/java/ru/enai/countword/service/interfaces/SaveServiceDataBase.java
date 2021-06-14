package ru.enai.countword.service.interfaces;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SaveServiceDataBase {
    void saveResultToDataBase(Map<String, Integer> result);
}
