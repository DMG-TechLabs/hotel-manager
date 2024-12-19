
package io.github.dmgtechlabs.db.files;

import java.util.regex.Pattern;

public class Images {
	private static final String ASSETS = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/assets/";

	public static String developer = ASSETS + "code-solid.png";
	public static String developerWhite = ASSETS + "code-solid-white.png";

	public static String mail = ASSETS + "envelope-solid.png";
	public static String mailWhite = ASSETS + "envelope-solid-white.png";

	public static String github = ASSETS + "github.png";
	public static String githubWhite = ASSETS + "github-white.png";

}
