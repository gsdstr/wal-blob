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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class World {

	public static final float WORLD_WIDTH = 2648;
	public static final float WORLD_HEIGHT = 1600;

	public final Random rand;

	public float offset;

	public World () {
		rand = new Random();
	}

	public void offset(float offset) {
		this.offset = 1 - offset * 2;
	}

	public void update (float deltaTime, float accelX) {

	}

}
