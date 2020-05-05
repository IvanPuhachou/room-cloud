package application.web;

import application.CountryDetectorUtil;
import application.Room;
import application.data.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/design")
public class DesignRoomController {

    private RoomRepository roomRepo;
    private final List<String> countriesNames = CountryDetectorUtil.getAllCountry();

    @Autowired
    public DesignRoomController(
            RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    @ModelAttribute(name = "room")
    public Room design() {
        return new Room();
    }


    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("country", countriesNames);
        return "design";
    }


    @PostMapping
    public String processDesign(
            Room room) {

        room.setOn(true);
        Room saved = roomRepo.save(room);

        return "success";
    }

}

