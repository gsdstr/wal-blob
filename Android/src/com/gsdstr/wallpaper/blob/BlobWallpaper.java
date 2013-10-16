package com.gsdstr.wallpaper.blob;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService;

/**
 * User: gsd
 * Date: 9/6/13
 * Time: 3:46 PM
 */
public class BlobWallpaper extends AndroidLiveWallpaperService {

	@Override
	public ApplicationListener createListener(boolean isPreview) {
		return new SuperWallpaper();
	}

	@Override
	public AndroidApplicationConfiguration createConfig () {
		return new AndroidApplicationConfiguration();
	}

	@Override
	public void offsetChange (ApplicationListener listener, float xOffset, float yOffset, float xOffsetStep, float yOffsetStep,
				  int xPixelOffset, int yPixelOffset) {
		Gdx.app.log("LiveWallpaper", "offset changed: " + xOffset + ", " + yOffset);
		((SuperWallpaper)listener).offsetChange(xOffset, yOffset);
	}

}