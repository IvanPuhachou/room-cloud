package application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import application.data.RoomRepository;
import application.web.OneRoomController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneRoomControllerTest {

    private static Long ROOM_ID = 1408L;

    private static final String HEADER_WITH_IP = "X-FORWARDED-FOR";
    private static final String UNITED_STATES_IP = "206.190.36.45";
    private static final String UNITED_STATES_NAME = "United States";

    private static final String BELARUS_IP = "37.215.56.46";
    private static final String BELARUS_NAME = "Belarus";

    @Mock
    private RoomRepository roomRepo;
    @Mock
    private HttpServletRequest testBelarusRequest;
    @Mock
    private HttpServletRequest testUsaRequest;

    private Room belarusRoom = new Room();

    @Before
    public void mockRepo() {

        belarusRoom.setCountryName(BELARUS_NAME);
        when(roomRepo.findById(ROOM_ID)).thenReturn(Optional.of(belarusRoom));
        when(testBelarusRequest.getHeader(HEADER_WITH_IP)).thenReturn(BELARUS_IP);
        when(testUsaRequest.getHeader(HEADER_WITH_IP)).thenReturn(UNITED_STATES_IP);
    }

    @Test
    public void testChangeLight() {
        OneRoomController oneRoomController = new OneRoomController(roomRepo, new CountryDetectorUtil());
        oneRoomController.setRoom(belarusRoom);

        Assert.assertEquals(oneRoomController.openRoom(ROOM_ID, testBelarusRequest), "oneRoom");
        Assert.assertEquals(oneRoomController.openRoom(ROOM_ID, testUsaRequest), "accessError");

    }


}
