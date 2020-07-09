package cn.linter.oasys.service;

import cn.linter.oasys.entity.Flight;
import com.github.pagehelper.PageInfo;


public interface FlightService {
    Flight getFlightById(int id);

    PageInfo<?> getFlight(int pageNumber, int pageSize, String cityFromName, String cityToName,
                          String flightCompanyName, String depDate,int ordering);
    PageInfo<?> getFlightDatePrice(int pageNumber, int pageSize, String cityFromName, String cityToName,
                          String flightCompanyName, String depDate,int ordering);

    int updateFlight(Flight flight);

    int addFlight(Flight flight);

    void deleteFlight(Integer[] ids);
}
