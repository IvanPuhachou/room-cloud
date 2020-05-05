/*
I used GeoLite2 data created by MaxMind, available from https://www.maxmind.com
 */
package application;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component
public class CountryDetectorUtil {


    public static String getCountryByIp(String ipAddress) {
        String dbLocation = "src\\main\\resources\\geoLite\\GeoLite2-City.mmdb";
        File database = new File(dbLocation);

        try {
            DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
            CountryResponse response = dbReader.country(InetAddress.getByName(ipAddress));
            return response.getRegisteredCountry().getName();
        } catch (IOException e) {
            return e.getMessage();
        } catch (GeoIp2Exception e) {
            return e.getMessage();
        }
    }

    public static List<String> getAllCountry() {
        List<String> allCountries = new ArrayList<>();

        String[] countryCodes = Locale.getISOCountries();

        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);

            allCountries.add(obj.getDisplayCountry(Locale.ENGLISH));
            Collections.sort(allCountries);
        }
        return allCountries;
    }

    private static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

    public String getCountryByRequest(HttpServletRequest request) {
        String ip = getClientIp(request);
        // Belarus ip
        //  ip = "37.215.56.46";
        return getCountryByIp(ip);
    }
}
