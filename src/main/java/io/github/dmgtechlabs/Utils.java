/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.dmgtechlabs;

/**
 *
 * @author kdesp73
 */
public class Utils {

	public static Object[] appendFront(Object item, Object... values) {
		Object[] params = new Object[values.length + 1];
		params[0] = item;
		System.arraycopy(values, 0, params, 1, values.length);

		return params;
	}

	public static Object[] appendBack(Object item, Object... values) {
		Object[] params = new Object[values.length + 1];
		System.arraycopy(values, 0, params, 0, values.length);
		params[values.length] = item;

		return params;
	}

	public class HTML {

		public static String header(int i, String str) {
			if (i > 6) {
				i = 6;
			}
			if (i < 1) {
				i = 1;
			}

			return "<h" + i + ">" + str + "</h" + i + ">";
		}

		public static String paragraph(String str) {
			return "<p>" + str + "</p>";
		}

		public static String ul(String... items) {
			StringBuilder sb = new StringBuilder();
			sb.append("<ul>");
			for (String item : items) {
				sb.append("<li>").append(item).append("</li>");
			}
			sb.append("</ul>");
			return sb.toString();
		}

		public static String div(String content) {
			return "<div>" + content + "</div>";
		}

		public static String span(String content) {
			return "<span>" + content + "</span>";
		}

		public static String anchor(String href, String text) {
			return "<a href=\"" + href + "\">" + text + "</a>";
		}

		public static String img(String src, String alt) {
			return "<img src=\"" + src + "\" alt=\"" + alt + "\" />";
		}

		public static String button(String text) {
			return "<button>" + text + "</button>";
		}

		public static String input(String type, String name, String value) {
			return "<input type=\"" + type + "\" name=\"" + name + "\" value=\"" + value + "\" />";
		}

		public static String form(String action, String method, String content) {
			return "<form action=\"" + action + "\" method=\"" + method + "\">" + content + "</form>";
		}

		public static String table(String... rows) {
			StringBuilder sb = new StringBuilder();
			sb.append("<table>");
			for (String row : rows) {
				sb.append("<tr>").append(row).append("</tr>");
			}
			sb.append("</table>");
			return sb.toString();
		}

		public static String tableRow(String... cells) {
			StringBuilder sb = new StringBuilder();
			for (String cell : cells) {
				sb.append("<td>").append(cell).append("</td>");
			}
			return sb.toString();
		}

		public static String orderedList(String... items) {
			StringBuilder sb = new StringBuilder();
			sb.append("<ol>");
			for (String item : items) {
				sb.append("<li>").append(item).append("</li>");
			}
			sb.append("</ol>");
			return sb.toString();
		}

		public static String bold(String text) {
			return "<b>" + text + "</b>";
		}

		public static String italic(String text) {
			return "<i>" + text + "</i>";
		}

		public static String code(String text) {
			return "<code>" + text + "</code>";
		}

		public static String blockquote(String text) {
			return "<blockquote>" + text + "</blockquote>";
		}

		public static String preformatted(String text) {
			return "<pre>" + text + "</pre>";
		}
	}

}
