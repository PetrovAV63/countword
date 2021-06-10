package ru.enai.countword.service.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface SaveService {
    <T> String saveService(T t);
}
