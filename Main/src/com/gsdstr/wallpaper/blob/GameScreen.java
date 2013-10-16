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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;

	Game game;

	int state;
	Vector3 touchPoint;
	SpriteBatch batcher;
	World world;
	WorldRenderer renderer;

	protected float deskOffset = 0.5f;

	public GameScreen (Game game, Assets assets) {
		this.game = game;

		state = GAME_RUNNING;
		touchPoint = new Vector3();
		batcher = new SpriteBatch();
		world = new World();
		renderer = new WorldRenderer(batcher, world, assets);
	}

	public void update (float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;

		switch (state) {
			case GAME_READY:
				updateReady();
				break;
			case GAME_RUNNING:
				updateRunning(deltaTime);
				break;
			default:
				break;
		}

	}

	private void updateReady () {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
		}
	}

	private void updateRunning (float deltaTime) {
		ApplicationType appType = Gdx.app.getType();
		// should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
		if (appType == ApplicationType.Android || appType == ApplicationType.iOS) {
			world.update(deltaTime, 0);
		} else {
			float accel = 0;
			if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)){
				deskOffset -= 0.1f;
				if (deskOffset < 0)
					deskOffset = 0;

			}
			if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)){
				deskOffset += 0.1f;
				if (deskOffset > 1)
					deskOffset = 1;


			}
			offsetChange(deskOffset, 1f);
			world.update(deltaTime, accel);
		}
	}

	public void draw (float deltaTime) {
		switch (state) {
			case GAME_READY:
				//presentReady();
				break;
			case GAME_RUNNING:
				Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
				renderer.render();
				break;
		}
	}

	@Override
	public void render (float delta) {
		update(delta);
		draw(delta);
	}

	@Override
	public void resize (int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void show () {
		if (state == GAME_READY){
			state = GAME_RUNNING;
			draw(0);
		}
	}

	@Override
	public void hide () {
		if (state == GAME_RUNNING) state = GAME_READY;
	}

	@Override
	public void pause () {
		if (state == GAME_RUNNING) state = GAME_READY;
	}

	@Override
	public void resume () {
		if (state == GAME_READY){
			state = GAME_RUNNING;
			draw(0);
		}
	}

	@Override
	public void dispose () {
	}

	protected float lastOffset = Float.MIN_VALUE;

	public void offsetChange(float xOffset, float yOffset) {
		if (lastOffset == Float.MIN_VALUE){
			lastOffset = xOffset;
			return;
		}
		world.offset(xOffset);
		renderer.calc();
		lastOffset = xOffset;
	}
}
