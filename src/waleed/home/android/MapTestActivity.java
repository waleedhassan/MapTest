package waleed.home.android;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MapTestActivity extends MapActivity implements LocationListener{
    private MapView mymap ;
    private static final boolean DISPLAYZOOMONMAP = true ; 
    private MapController mController ;
    private LocationManager mlocationMan;
    private List<String> provider ;
    private Criteria criteria ;
    private String bestProvider;
    private GeoPoint myPoint ;
    
    /*
     * 
    */
    class MyOverlay extends Overlay
    {

        @Override
        public boolean draw(Canvas canvas, MapView mapView,boolean shadow, long when) 
         {
            super.draw(canvas, mapView, shadow);                   
 
            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView.getProjection().toPixels(myPoint, screenPts);
 
            //---add the marker---
            Bitmap bmp = BitmapFactory.decodeResource(
                getResources(), R.drawable.mapmarker);            
            canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);         
            return true;
        }
    } 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mymap = (MapView)findViewById(R.id.mapView);
        mController = mymap.getController();        
        mymap.setBuiltInZoomControls(DISPLAYZOOMONMAP);   
        mlocationMan = (LocationManager)getSystemService(LOCATION_SERVICE);
        provider = mlocationMan.getAllProviders();
        criteria = new Criteria();
        bestProvider = mlocationMan.getBestProvider(criteria, false);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mlocationMan.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mlocationMan.requestLocationUpdates(bestProvider, 20000, 1, this);
    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }
    /*
     * Just for proof of concept that you can zoom In/Out with your own 
     * action. you can test it in emulator 
     * you can choose another action instead of KeyDown
     * */
    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_1)
            mController.zoomIn();
        if(keyCode == KeyEvent.KEYCODE_3)
            mController.zoomOut();
        return super.onKeyDown(keyCode, event);
    }
    */

    @Override
    public void onLocationChanged(Location arg0) {
        // TODO Auto-generated method stub
        myPoint = new GeoPoint((int)(arg0.getLatitude()  * 1E6),(int) (arg0.getLongitude()* 1E6));
        mController.animateTo(myPoint);
        mController.setZoom(17); 
        MyOverlay mapOverlay = new MyOverlay();
        List<Overlay> listOfOverlays = mymap.getOverlays();
        listOfOverlays.clear();
        listOfOverlays.add(mapOverlay);
        mymap.invalidate();
        Toast.makeText(this, "OK++", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub
        
    }
}