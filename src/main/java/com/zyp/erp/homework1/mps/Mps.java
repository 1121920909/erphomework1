package com.zyp.erp.homework1.mps;

import java.util.ArrayList;

/**
 * @author ZYP
 */
public class Mps {
  ArrayList<Time> times;
  private int safeStock;
  private int leadTime;
  private int dtf;
  private int pt;
  private int batch;
  private int curentStock;

  public int getCurentStock() {
    return curentStock;
  }

  public void setCurentStock(int curentStock) {
    this.curentStock = curentStock;
  }

  public Mps() {
  }

  public Mps(int safeStock, int leadTime, int dtf, int pt, int batch, int curentStock) {
    this.safeStock = safeStock;
    this.leadTime = leadTime;
    this.dtf = dtf;
    this.pt = pt;
    this.batch = batch;
    this.curentStock = curentStock;
  }

  public ArrayList<Time> getTimes() {
    return times;
  }

  public void setTimes(ArrayList<Time> times) {
    this.times = times;
  }

  public int getSafeStock() {
    return safeStock;
  }

  public void setSafeStock(int safeStock) {
    this.safeStock = safeStock;
  }

  public int getLeadTime() {
    return leadTime;
  }

  public void setLeadTime(int leadTime) {
    this.leadTime = leadTime;
  }

  public int getDtf() {
    return dtf;
  }

  public void setDtf(int dtf) {
    this.dtf = dtf;
  }

  public int getPt() {
    return pt;
  }

  public void setPt(int pt) {
    this.pt = pt;
  }

  public int getBatch() {
    return batch;
  }

  public void setBatch(int batch) {
    this.batch = batch;
  }
}
