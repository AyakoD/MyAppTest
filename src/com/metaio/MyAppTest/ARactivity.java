package com.metaio.MyAppTest;

import android.util.Log;
import android.view.View;

import com.metaio.Template.R;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.sdk.jni.MetaioSDK;
import com.metaio.sdk.jni.Vector3d;
import com.metaio.tools.io.AssetsManager;

/**
 * Created by Ayako on 03/03/15.
 */
public class ARactivity extends MetaioSDKViewActivity{
    private String mTrackinfFile;
    private IGeometry mMan;
    private IGeometry mTiger;

    @Override
    protected int getGUILayout() {

        return R.layout.ar_view;
    }

    @Override
    protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
        return null;
    }

    @Override
    protected void loadContents() {
        mTrackinfFile = AssetsManager.getAssetPath("assets/TrackingData_MakerLessFast.xml");
        boolean result = metaioSDK.setTrackingConfiguration(mTrackinfFile);
        MetaioDebug.log("Tracking data loaded: " + result);
        String modelPath = AssetsManager.getAssetPath("assets/metaioman.md2");
        if(modelPath != null){
            mMan=metaioSDK.createGeometry(modelPath);
            if (mMan != null){
                mMan.setScale(new Vector3d(4.0f,4.0f,4.0f));
                mMan.setVisible(true);
                MetaioDebug.log("Loaded geometry: " + modelPath);
            }
            else MetaioDebug.log(Log.ERROR, "error loading geometry: "+ modelPath);
        }

        String modelPath1 = AssetsManager.getAssetPath("assets/tiger.md2");
        if(modelPath1 != null){
            mTiger=metaioSDK.createGeometry(modelPath1);
            if (mTiger != null){
                mTiger.setScale(new Vector3d(10.0f,10.0f,10.0f));
                mTiger.setVisible(true);
                MetaioDebug.log("Loaded geometry: " + modelPath1);
            }
            else MetaioDebug.log(Log.ERROR, "error loading geometry: "+ modelPath1);
        }
    }

    public void onManButtonClick(View v){
        mMan.setVisible(true);
        mTiger.setVisible(false);

    }

    public void onTigerButtonClick(View v){
        mMan.setVisible(false);
        mTiger.setVisible(true);

    }

    @Override
    protected void onGeometryTouched(IGeometry geometry) {

    }

}
