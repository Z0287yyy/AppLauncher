
package com.chris.applauncher.utils.base;

import java.util.List;
import java.util.Locale;




import com.chris.applauncher.AppLauncherApplication;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


public class LocationUtils {
	
	private static Address curAddress;
	
	public static synchronized Address getLastAddress() {
		return curAddress;
	}
	
	public static interface LocationHandler {
		public void didGetAddressFailed(Exception e);
		public void didGetAddress(Address address);
	}

	/**
	 * get Address info
	 * @param activity, the activity call the location
	 * @return address
	 */
	public static void getAddress(final LocationHandler handler) {
		Address address = null;
		
		final Context context = AppLauncherApplication.getAppContext();
		LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		try {
			Criteria criteria = new Criteria(); 
			criteria.setAccuracy(Criteria.ACCURACY_COARSE);
			criteria.setAltitudeRequired(false);
			criteria.setBearingRequired(false); 
			criteria.setCostAllowed(false);
			criteria.setPowerRequirement(Criteria.POWER_LOW);
			
			Location location = null;
			if (location == null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			}
			if (location == null && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			}
			if (location == null && locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
				location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
			}
			//location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, true));
			
			if(location != null) {
				Geocoder gc = new Geocoder(context, Locale.getDefault());
				List<Address> lstAddress = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
				if(lstAddress.size() > 0) {
					address = lstAddress.get(0);
				}
			}
		} catch (Exception e) {
			//do nothing
		}
		
		if (address != null) {
			if (handler != null) {
				handler.didGetAddress(address);
			}
		} else {
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new GpsLocationListner(locationManager, handler));
		}
	}
	
	private static class GpsLocationListner implements LocationListener {
		private LocationManager locationManager;
		private LocationHandler handler;
		public GpsLocationListner(LocationManager locationManager, LocationHandler handler) {
			this.locationManager = locationManager;
			this.handler = handler;
		}
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			System.out.println("LocationUtils::onStatusChanged::" + provider + ", " + status + ", " + extras);
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			System.out.println("LocationUtils::onProviderEnabled::" + provider);
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			System.out.println("LocationUtils::onProviderDisabled::" + provider);
			if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && provider.equals(LocationManager.GPS_PROVIDER)) {
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, new GpsLocationListner(locationManager, handler));
			} else if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER) && provider.equals(LocationManager.NETWORK_PROVIDER)) {
				locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1000, 10, new GpsLocationListner(locationManager, handler));
			} else {
				handler.didGetAddressFailed(new NullPointerException("Can't get Address: " + provider));
			}
		}
		
		@Override
		public void onLocationChanged(Location location) {
			if (locationManager == null) {
				return;
			}
			locationManager.removeUpdates(this);
			locationManager = null;
			
			Address address = null;
			Exception getAddException = null;
			final Context context = AppLauncherApplication.getAppContext();
			try {
				Geocoder gc = new Geocoder(context, Locale.getDefault());
				List<Address> lstAddress = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
				if(lstAddress.size() > 0) {
					address = lstAddress.get(0);
				}
			} catch (Exception e) {
				getAddException = e;
			}
			if (address != null) {
				if (handler != null) {
					handler.didGetAddress(address);
				}
			} else {
				if (handler != null) {
					if (getAddException != null) {
						handler.didGetAddressFailed(getAddException);
					} else {
						handler.didGetAddressFailed(new NullPointerException("Can't get Address."));
					}
				}
			}
		}
	};
	
	public static String getCityState(Address address) {
		String location = null;
		if(address != null) {
			String city = address.getLocality();
			if(city == null) {
				city = address.getSubAdminArea();
			}
			String state = address.getAdminArea();
			String [] locs = {city, state};
			for(int i =0; i < locs.length; i++) {
				if(locs[i] != null) {
					if(location != null && location.length() > 0) {
						location += (", " + locs[i]);
					} else {
						location = locs[i];
					}
				}
			}
		}
		return location;
	}

	public static String getCityStateContry(Address address) {
		String location = null;
		if(address != null) {
			String city = address.getLocality();
			if(city == null) {
				city = address.getSubAdminArea();
			}
			String state = address.getAdminArea();
			String country = address.getCountryName();
			String [] locs = {city, state, country};
			for(int i =0; i < locs.length; i++) {
				if(locs[i] != null) {
					if(location != null && location.length() > 0) {
						location += (", " + locs[i]);
					} else {
						location = locs[i];
					}
				}
			}
		}
		return location;
	}
}
