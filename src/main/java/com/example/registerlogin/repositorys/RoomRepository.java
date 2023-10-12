package com.example.registerlogin.repositorys;

import com.example.registerlogin.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Nullable
    List<Room> findAll();
    @Nullable
    List<Room> findById();
    @Nullable
    List<Room> findByHotel_Id(Integer id);
}
