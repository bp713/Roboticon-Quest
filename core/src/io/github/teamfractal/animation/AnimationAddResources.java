package io.github.teamfractal.animation;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import io.github.teamfractal.entity.Player;
import io.github.teamfractal.screens.AbstructAnimationScreen;

public class AnimationAddResources implements IAnimation {
	private final Player player;
	private final int energy;
	private final int food;
	private final int ore;
	private float time;
	BitmapFont font = new BitmapFont();


	public AnimationAddResources(Player player, int energy, int food, int ore) {
		time = 0;
		this.player = player;
		this.energy = energy;
		this.food = food;
		this.ore = ore;

		// System.out.println("Phase 5 Res Gen: " + generateResourceString());
	}

	private String resStr(int resCount, String type) {
		if (resCount == 0) {
			return "-- " + type + "   ";
		}
		return (resCount > 0 ? "+" : "-") + resCount + " " + type + "   ";
	}

	private static final float animationLength = 3;

	@Override
	public boolean tick(float delta, AbstructAnimationScreen screen, Batch batch) {
		time += delta;
		if (time > animationLength) {
			return true;
		}

		font.setColor(1,1,1, (float)(1 - time / animationLength));
		font.draw(batch, generateResourceString(), 20, 30);
		return false;
	}

	private IAnimationFinish callback;
	@Override
	public void setAnimationFinish(IAnimationFinish callback) {
		this.callback = callback;
	}

	public void callAnimationFinish() {
		if (callback != null)
			callback.OnAnimationFinish();
	}

	private String generateResourceString() {
		return (resStr(energy, "Energy")
				+ resStr(food, "Food")
				+ resStr(ore, "Ore")).trim();
	}
}