/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.gsdstr.wallpaper.blob;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;

public class SuperWallpaper extends Game {

	FPSLogger _fpsLogger;
	GameScreen _gameScreen;
	Assets _assets;

	@Override
	public void create () {
		Settings.load();
		_assets = new Assets();
		_assets.load();
		_gameScreen = new GameScreen(this, _assets);
		setScreen(_gameScreen);
		_fpsLogger = new FPSLogger();
	}
	
	@Override
	public void render() {
		super.render();
		_fpsLogger.log();
	}

	/** {@link Game#dispose()} only calls {@link Screen#hide()} so you need to override {@link Game#dispose()} in order to call
	 * {@link Screen#dispose()} on each of your screens which still need to dispose of their resources. SuperWallpaper doesn't
	 * actually have such resources so this is only to complete the example. */
	@Override
	public void dispose () {
		super.dispose();
		getScreen().dispose();
		_assets.dispose();
	}

	public void offsetChange(float xOffset, float yOffset) {
		if (_gameScreen != null)
			_gameScreen.offsetChange(xOffset, yOffset);
	}
}
