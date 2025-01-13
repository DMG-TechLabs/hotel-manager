package io.github.dmgtechlabs;

import io.github.dmgtechlabs.models.Room;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.QueryBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import io.github.kdesp73.databridge.helpers.SQLogger;

public class Filters {
	public State state;

	public List<Room.Type> types = new ArrayList<>();
	public Range priceRange;

	public Filters(State state) {
		this.state = state;
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
		StringBuilder query = new StringBuilder(new QueryBuilder()
                .select()
                .from("room").build()).append(" WHERE (");

		for (int i = 0; i < this.types.size(); i++) {
			Room.Type type = this.types.get(i);
			if (type == null) {
				continue;
			}

			if (i == this.types.size() - 1) {
				query.append("type = ").append(type.getValue());
			} else {
				query.append("type = ").append(type.getValue()).append(" OR ");
			}
		}
		query.append(")");
		
		query.append(" AND price >= ").append(this.priceRange.min);
		query.append(" AND price <= ").append(this.priceRange.max);
		
		query.append(" AND occupied = false");
		query.append(" AND room_hotel_fk = ").append(this.state.activeHotelId).append(";");
		
		return query.toString();
	}

	public List<Room> search() {
		String query = toQuery();
		System.out.println(query);
		List<Room> result = new ArrayList<>();
		try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
			ResultSet rs = conn.executeQuery(query);
			while (rs.next()) {
				result.add(Room.parseRs(rs));
			}
			rs.close();
		} catch (Exception e) {
//			SQLogger.getLogger().log("Failed running filter query", e);
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
