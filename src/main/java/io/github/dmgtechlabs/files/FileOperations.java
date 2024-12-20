
package io.github.dmgtechlabs.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileOperations {

	private static String os = System.getProperty("os.name").toLowerCase();
	private static char slash = (os.contains("windows")) ? '\\' : '/';
	public static HashSet<String> acceptedExtensions = new HashSet<>();
	public static Boolean throwErrors = false;
	public static int maxDepth = 10;

	private static boolean chechPath(String path) throws IllegalArgumentException {
		if (path == null || path == "" || path.lastIndexOf(slash) <= 0 || path.lastIndexOf(".") < 0) {
			if (throwErrors)
				throw new IllegalArgumentException("Invalid path");
			return true;
		}
		return false;
	}

	public static String getDirectoryFromPath(String path) throws IllegalArgumentException {
		if (chechPath(path))
			return null;
		return path.substring(0, path.lastIndexOf('/')) + slash;
	}

	public static String getFilenameFromPath(String path) throws IllegalArgumentException {
		if (chechPath(path))
			return null;
		return path.substring(path.lastIndexOf(slash) + 1, path.length());
	}

	public static String getJustFilenameFromPath(String path) throws IllegalArgumentException {
		if (chechPath(path))
			return null;
		return path.substring(path.lastIndexOf(slash) + 1, path.lastIndexOf('.'));
	}

	public static String getExtensionFromPath(String path) throws IllegalArgumentException {
		if (chechPath(path))
			return null;
		return path.substring(path.lastIndexOf('.') + 1, path.length());
	}

	public static List<String> findFiles(String directory, HashSet<String> extensions)
			throws IOException, IllegalArgumentException {
		HashSet<String> temp = acceptedExtensions;
		if (extensions != null) {
			acceptedExtensions = extensions;
		}
		try (Stream<Path> paths = Files.walk(Path.of(directory), 10)) {
			List<String> pathlist = paths
					.map(path -> (Files.isDirectory(path)
							&& path.toString().contains(".")
							&& acceptedExtensions.isEmpty()
							|| acceptedExtensions.contains(FileOperations.getExtensionFromPath(path.toString())))
									? path.toString() + slash
									: path.toString())
					.collect(Collectors.toList());

			acceptedExtensions = temp;
			return pathlist;

		} catch (IOException e) {
			acceptedExtensions = temp;
			throw e;
		}
	}

	public static List<String> getFilenames(List<String> paths) throws IllegalArgumentException {

		List<String> filenames = new ArrayList<>();
		for (String path : paths) {
			filenames.add(FileOperations.getFilenameFromPath(path));
		}

		return filenames;
	}

	public static String readFile(String path) {
		String content = "";
		try {
			// Create a FileReader to read the file
			FileReader fileReader = new FileReader(path);

			// Create a BufferedReader to efficiently read the file line by line
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content += line + "\n";
			}

			// Close the BufferedReader and FileReader when done
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

}
