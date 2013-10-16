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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	public Texture background;
	public TextureRegion backgroundRegion;

	public Texture blob;
	public TextureRegion blobRegion;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public void load () {
		if (background != null)
			return;

		background = loadTexture("data/background.png");
		background.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
		backgroundRegion = new TextureRegion(background, 0, 0, 1024, 1024);


		blob = loadTexture("data/blob.png");
		blob.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
		blobRegion = new TextureRegion(blob, 0, 0, blob.getWidth(), blob.getHeight());
	}

	public void dispose(){
		background.dispose();
		blob.dispose();
	}
}
