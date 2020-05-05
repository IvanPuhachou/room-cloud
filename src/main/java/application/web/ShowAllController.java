package application.web;

import application.Room;
import application.data.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/showAll")
public class ShowAllController {
    private RoomRepository roomRepo;

    @Autowired
    public ShowAllController(
            RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }


    @ModelAttribute(name = "rooms")
    public List<Room> roomList() {
        List<Room> roomArrayList = new ArrayList<>();
        roomRepo.findAll().forEach(i -> roomArrayList.add(i));
        return roomArrayList;
    }

    @GetMapping
    public String showDesignForm() {
        return "showAll";
    }
}
