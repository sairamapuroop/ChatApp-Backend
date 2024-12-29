package com.substring.chat.chat_app_backend.repositores;

import com.substring.chat.chat_app_backend.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

    //get room using roomId
     Room findByRoomId(String roomId);

}
