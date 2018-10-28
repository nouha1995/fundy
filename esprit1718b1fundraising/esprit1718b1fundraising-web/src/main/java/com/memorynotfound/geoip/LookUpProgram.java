package com.memorynotfound.geoip;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
public class LookUpProgram {

	
	public static void main(String[] args) throws UnknownHostException {
		System.out.println(InetAddress.getLocalHost());
		LookUpProgram obj = new LookUpProgram();
		ServerLocation location = obj.getLocation("206.190.36.45");
		System.out.println(location.getCountryName());
	}

	 public ServerLocation getLocation(String ipAddress) {

			File file = new File(
			    "C:\\Users\\HP\\git\\esprit1718b1fundraising\\esprit1718b1fundraising\\esprit1718b1fundraising-web\\src\\main\\webapp\\resources\\GeoLiteCity.dat");
			return getLocation(ipAddress, file);

		  }

		  public ServerLocation getLocation(String ipAddress, File file) {

			ServerLocation serverLocation = null;

			try {

			serverLocation = new ServerLocation();

			LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
			Location locationServices = lookup.getLocation(ipAddress);

			serverLocation.setCountryCode(locationServices.countryCode);
			serverLocation.setCountryName(locationServices.countryName);
			serverLocation.setRegion(locationServices.region);
			serverLocation.setRegionName(regionName.regionNameByCode(
		             locationServices.countryCode, locationServices.region));
			serverLocation.setCity(locationServices.city);
			serverLocation.setPostalCode(locationServices.postalCode);
			serverLocation.setLatitude(String.valueOf(locationServices.latitude));
			serverLocation.setLongitude(String.valueOf(locationServices.longitude));

			} catch (IOException e) {
				System.err.println(e.getMessage());
			}

			return serverLocation;

		  }
}
