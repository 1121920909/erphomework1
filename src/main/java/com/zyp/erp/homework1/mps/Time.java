package com.zyp.erp.homework1.mps;

/**
 * @author ZYP
 */
public class Time {
  private double time;
  private double forecasts;
  private double order;
  private double scheReceipts;
  private double quanGross;
  private double stock;
  private double netRequire;
  private double input;
  private double output;
  private double sales;

  public Time() {
  }

  public Time(double time, double forecasts, double order, double scheReceipts, double quanGross, double stock, double netRequire, double input, double output, double sales) {
    this.time = time;
    this.forecasts = forecasts;
    this.order = order;
    this.scheReceipts = scheReceipts;
    this.quanGross = quanGross;
    this.stock = stock;
    this.netRequire = netRequire;
    this.input = input;
    this.output = output;
    this.sales = sales;
  }

  public double getTime() {
    return time;
  }

  public void setTime(double time) {
    this.time = time;
  }

  public double getForecasts() {
    return forecasts;
  }

  public void setForecasts(double forecasts) {
    this.forecasts = forecasts;
  }

  public double getOrder() {
    return order;
  }

  public void setOrder(double order) {
    this.order = order;
  }

  public double getScheReceipts() {
    return scheReceipts;
  }

  public void setScheReceipts(double scheReceipts) {
    this.scheReceipts = scheReceipts;
  }

  public double getQuanGross() {
    return quanGross;
  }

  public void setQuanGross(double quanGross) {
    this.quanGross = quanGross;
  }

  public double getStock() {
    return stock;
  }

  public void setStock(double stock) {
    this.stock = stock;
  }

  public double getNetRequire() {
    return netRequire;
  }

  public void setNetRequire(double netRequire) {
    this.netRequire = netRequire;
  }

  public double getInput() {
    return input;
  }

  public void setInput(double input) {
    this.input = input;
  }

  public double getOutput() {
    return output;
  }

  public void setOutput(double output) {
    this.output = output;
  }

  public double getSales() {
    return sales;
  }

  public void setSales(double sales) {
    this.sales = sales;
  }

  @Override
  public String toString() {
    return "Time{" +
            "time=" + time +
            ", forecasts=" + forecasts +
            ", order=" + order +
            ", scheReceipts=" + scheReceipts +
            ", quanGross=" + quanGross +
            ", stock=" + stock +
            ", netRequire=" + netRequire +
            ", input=" + input +
            ", output=" + output +
            ", sales=" + sales +
            '}';
  }
}
