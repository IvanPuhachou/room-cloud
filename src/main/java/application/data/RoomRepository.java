package application.data;

import org.springframework.data.repository.CrudRepository;
import application.Room;

public interface RoomRepository   extends CrudRepository<Room, Long> {

}
