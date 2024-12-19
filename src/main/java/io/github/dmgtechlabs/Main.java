package io.github.dmgtechlabs;

import io.github.dmgtechlabs.models.Room;
import io.github.kdesp73.databridge.helpers.Config;

/**
 *
 * @author kdesp73
 */
public class Main {
	public static void main(String[] args) {
		new Room(1, 1, Room.Type.SINGLE, 50.2f).insert();
	}
}
