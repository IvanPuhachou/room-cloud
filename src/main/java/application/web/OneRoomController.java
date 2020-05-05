package application.web;

import application.CountryDetectorUtil;
import application.Room;
import application.data.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/oneRoom")
public class OneRoomController {


    private RoomRepository roomRepo;
    private CountryDetectorUtil countryDetectorUtil;
    private Room room;


    @ModelAttribute(name = "room")
    public Room getRoom() {
        if (room == null) {
            room = new Room();
        }
        return room;
    }

    // For test
    public void setRoom(Room room) {
        this.room = room;
    }

    @Autowired
    public OneRoomController(
            RoomRepository roomRepo,
            CountryDetectorUtil countryDetectorUtil) {

        this.roomRepo = roomRepo;
        this.countryDetectorUtil = countryDetectorUtil;
    }

    @RequestMapping(value = "/in")
    public String openRoom(Long roomId, HttpServletRequest request) {
        Optional<Room> oRoom = roomRepo.findById(roomId);
        if (oRoom.isPresent()) {
            this.room.initFromTemplate(oRoom.get());
        }
        if (room.getCountryName().equals(countryDetectorUtil.getCountryByRequest(request))) {
            return "oneRoom";
        } else {
            return "accessError";
        }
    }

    @RequestMapping(value = "/change")
    public String changeLight(Long roomId,
                              HttpServletRequest request) {
        Optional<Room> oRoom = roomRepo.findById(roomId);
        if (oRoom.isPresent()) {
            Room room = oRoom.get();
            room.setOn(!room.isOn());
            roomRepo.save(room);
            this.room.setOn(!room.isOn());
        }
        return "redirect:/oneRoom/in?roomId=" + room.getId();
    }
}
