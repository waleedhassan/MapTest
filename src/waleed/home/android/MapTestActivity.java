package waleed.home.android;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.os.Bundle;
import android.view.KeyEvent;

public class MapTestActivity extends MapActivity {
    private MapView mymap ;
    private static final boolean DISPLAYZOOMONMAP = true ; 
    private MapController mController ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mymap = (MapView)findViewById(R.id.mapView);
        mController = mymap.getController();        
        mymap.setBuiltInZoomControls(DISPLAYZOOMONMAP);        
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
}