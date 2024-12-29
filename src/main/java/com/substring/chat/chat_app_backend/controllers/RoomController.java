package com.substring.chat.chat_app_backend.controllers;

import com.substring.chat.chat_app_backend.config.AppConstants;
import com.substring.chat.chat_app_backend.entities.Message;
import com.substring.chat.chat_app_backend.entities.Room;
import com.substring.chat.chat_app_backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(AppConstants.FRONT_END_BASE_URL)
public class RoomController {

    @Autowired
    RoomService roomService;

    //create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {

        Room room = roomService.findRoomById(roomId);
        if (room != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room already Exists!");
        }

        room = roomService.createRoom(roomId);
        if (room == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong, couldn't create room!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    ;

    //get room : join
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {

        Room room = roomService.findRoomById(roomId);

        if (room == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room not found!");
        }

        return ResponseEntity.ok(room);
    }

    ;


    //get messages of room
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {

        Room room = roomService.findRoomById(roomId);

        if (room == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //get messages :
        //pagination
        List<Message> messages = room.getMessages();

        int start = Math.max(0, messages.size() - (page + 1)*size);
        int end = Math.min(messages.size(), start + size);

        List<Message> paginatedMessages = messages.subList(start, end);

        return ResponseEntity.ok(paginatedMessages);
    };

}
