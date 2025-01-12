package io.github.dmgtechlabs;

import io.github.dmgtechlabs.models.Room;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.QueryBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class Filters {

	public List<Room.Type> types = new ArrayList<>();
	public Range priceRange;

	public Filters() {
	}

	public void setRange(float min, float max) {
		priceRange = new Range(min, max);
	}

	public void addType(Room.Type type) {
		if (type == null) {
			return;
		}
		types.add(type);
	}

	public String toQuery() {
		String query = new QueryBuilder()
			.select()
			.from("room").build() + " WHERE (";

		// TODO: add filters
		for (int i = 0; i < this.types.size(); i++) {
			Room.Type type = this.types.get(i);
			if (type == null) {
				continue;
			}

			if (i == this.types.size() - 1) {
				query += "type = " + type.getValue();
			} else {
				query += "type = " + type.getValue() + " OR ";
			}
		}
		query += ")";
		
		query += " AND price >= " + this.priceRange.min;
		query += " AND price <= " + this.priceRange.max;
		
		return query;
	}

	public List<Room> search() {
		String query = toQuery();
		List<Room> result = new ArrayList<>();
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {
				result.add(Room.parseRs(rs));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return result;
	}

	private static class Range {

		public float min;
		public float max;

		Range() {
		}

		Range(float min, float max) {
			this.min = min;
			this.max = max;
		}

		boolean inside(float num) {
			return (num >= min && num <= max);
		}

		boolean outside(float num) {
			return (num < min || num > max);
		}
	}
}
