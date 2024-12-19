package io.github.dmgtechlabs.hotelManagment;

import java.util.ArrayList;
import java.util.List;
import io.github.kdesp73.databridge.*;

class HotelManagment implements HotelManagmentInterface {

  private List<String> db_query = new ArrayList<String>();
  private Database db = new Database();

  public boolean AddHotel() {

    this.db_query.add("");
    return false;
  }

  public boolean EditHotel() {
    this.db_query.add("");
    return false;
  }

  public boolean DeleteHotel() {
    this.db_query.add("");
    return false;
  }

  public boolean CommitChanges() {

    for (String query : this.db_query) {

    }

    return false;
  }

}
