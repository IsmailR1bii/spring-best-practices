package com.example.registerlogin.services;

import com.example.registerlogin.repositorys.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    
}
