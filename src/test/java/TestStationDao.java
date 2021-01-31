import dto.StationDescription;
import org.junit.Test;
import utils.StationDao;

import static org.junit.Assert.assertEquals;

public class TestStationDao {

    @Test
    public void test_import_first_station(){
        StationDao stationDao = new StationDao();

        StationDescription result = stationDao.getStationDescription(1);


        assertEquals(50.068435, result.getLatitude(), 0.1);
        assertEquals(1, result.getNumber());
        assertEquals(14.507169, result.getLongitude(), 0.1);
        assertEquals("Skalka", result.getName());
    }
}
