package ee.taltech.iti0202.exam.transit;

import java.util.List;

public class BusStop {

  private String name;

  private List<String> busLines;

  private List<String> currentBusesIn;

  public BusStop(String name, List<String> busLines) {
    this.name = name;
    this.busLines = busLines;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getBusLines() {
      return busLines;
  }

  public void setBusLines(List<String> busLines) {
    this.busLines = busLines;
  }

  public List<String> getCurrentBusesIn() {
    return currentBusesIn;
  }
}

