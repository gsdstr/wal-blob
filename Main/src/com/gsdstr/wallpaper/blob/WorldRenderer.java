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
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 740;
	static final float FRUSTUM_HEIGHT = 1280;
	static final float BLOB_WIDTH = 512;
	static final float BLOB_HEIGHT = 512;
	static final float BLOB_X = (World.WORLD_WIDTH - BLOB_WIDTH)/ 2;
	static final float BLOB_Y = World.WORLD_HEIGHT / 2 - BLOB_HEIGHT + 100;

	World world;
	OrthographicCamera cam;
	SpriteBatch batch;
	float factor = 1;
	boolean isLand = false;
	Assets assets;

	BitmapFont font;

	public WorldRenderer (SpriteBatch batch, World world, Assets assets) {
		this.world = world;
		this.cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.cam.position.set(World.WORLD_WIDTH / 2, World.WORLD_HEIGHT - FRUSTUM_HEIGHT / 2, 0);
		this.batch = batch;
		this.assets = assets;

		font = new BitmapFont(Gdx.files.internal("data/font/font.fnt"), Gdx.files.internal("data/font/font.png"), false);
		calc();
	}

	public void render () {
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}

	protected float background_x;
	protected float background_y;
	protected float background_w;
	protected float background_h;

	public void renderBackground () {
		batch.disableBlending();
		batch.begin();
		batch.draw(assets.backgroundRegion, background_x, background_y, background_w, background_h);
		batch.end();
	}

	protected float blob_x;
	protected float blob_y;
	protected float blob_w;
	protected float blob_h;

	public void renderObjects () {
		batch.enableBlending();
		batch.begin();
		batch.draw(assets.blobRegion, blob_x, blob_y, blob_w, blob_h);
		font.draw(batch, "fps:"+Gdx.graphics.getFramesPerSecond(), blob_x, blob_y);
		batch.end();
	}

	public void resize(int width, int height) {
		this.cam = new OrthographicCamera(width, height);
		factor = height / FRUSTUM_HEIGHT;
		isLand = width > height;
		this.cam.position.set(World.WORLD_WIDTH * factor / 2, World.WORLD_HEIGHT  * factor - height / 2, 0);
		calc();
	}

	public void calc() {
		float sOffset = isLand ? world.offset * 100 : world.offset * 200;
		background_x = sOffset + 0;
		background_y = 0;
		background_w = World.WORLD_WIDTH  * factor;
		background_h = World.WORLD_HEIGHT * factor;
		sOffset = isLand ? world.offset  / 0.06f : world.offset / 0.03f;
		blob_x = (sOffset + BLOB_X) * factor;
		blob_y = BLOB_Y * factor;
		blob_w = BLOB_WIDTH * factor;
		blob_h = BLOB_HEIGHT * factor;

	}
}
