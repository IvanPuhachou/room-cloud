package application;

import org.junit.Assert;
import org.junit.Test;

public class CountryDetectorUtilTest {

    private static final String UNITED_STATES_IP = "206.190.36.45";
    private static final String UNITED_STATES_NAME = "United States";

    private static final String BELARUS_IP = "37.215.56.46";
    private static final String BELARUS_NAME = "Belarus";



    @Test
    public void testIpDetector() {
        Assert.assertEquals(CountryDetectorUtil.getCountryByIp(UNITED_STATES_IP), UNITED_STATES_NAME);
        Assert.assertEquals(CountryDetectorUtil.getCountryByIp(BELARUS_IP), BELARUS_NAME);
    }
}
