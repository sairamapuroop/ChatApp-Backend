package com.substring.chat.chat_app_backend.services;

import com.substring.chat.chat_app_backend.entities.Room;
import com.substring.chat.chat_app_backend.repositores.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Room createRoom(String roomId) {

        if (roomRepository.findByRoomId(roomId) != null) {
            return null;
        }

        Room room = new Room();

        room.setRoomId(roomId);
        Room savedRoom = roomRepository.save(room);

        return savedRoom;
    }

    ;

    public Room findRoomById(String roomId) {

        return roomRepository.findByRoomId(roomId);
    }

    public void save(Room room) {
//        if(roomRepository.findByRoomId(room.getRoomId()) == null) {
//            return;
//        }
        roomRepository.save(room);
    }
}
