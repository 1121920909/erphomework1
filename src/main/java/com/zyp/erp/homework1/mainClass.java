package com.zyp.erp.homework1;
import com.zyp.erp.homework1.excelTool.excelTool;
import com.zyp.erp.homework1.mps.Mps;
import com.zyp.erp.homework1.mps.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author ZYP
 */
public class mainClass {
  private Mps mps;
  /**
   * 毛需求
   */
  private void computeQuanGross() {
    List<Time> times = mps.getTimes();
    Time time;
    for (int i = 0; i < times.size(); i++) {
      time = times.get(i);
      if (i <= mps.getDtf()) {
        time.setQuanGross(time.getOrder());
      } else if (i > mps.getPt()) {
        time.setQuanGross(time.getForecasts());
      } else {
        time.setQuanGross(Math.max(time.getForecasts(),time.getOrder()));
      }
    }
  }
  /**
   * 净需求、计划产出、预计库存
   */
  private void computStock() {
    List<Time> times = mps.getTimes();
    Time time;
    for (int i = 0; i < times.size(); i++) {
      time = times.get(i);
      if (i == 0) {
        //净需求
        time.setNetRequire(time.getQuanGross() - mps.getCurentStock() - time.getScheReceipts() + mps.getSafeStock());
        //计划产出
        if (time.getNetRequire() > 0) {
          int n = 1;
          while (true) {
            if (n * mps.getBatch() > time.getNetRequire()) {
              break;
            }
            n++;
          }
          time.setOutput(n * mps.getBatch());
        } else {
          time.setOutput(0);
        }
        //预计可用库存
        time.setStock(mps.getCurentStock() + time.getScheReceipts() - time.getQuanGross() + time.getOutput());
      } else {
        //净需求
        time.setNetRequire(time.getQuanGross() - times.get(i - 1).getStock() - time.getScheReceipts() + mps.getSafeStock());
        //计划产出量
        if (time.getNetRequire() > 0) {
          int n = 1;
          while (true) {
            if (n * mps.getBatch() > time.getNetRequire()) {
              break;
            }
            n++;
          }
          time.setOutput(n * mps.getBatch());
        } else {
          time.setOutput(0);
        }
        //预计可用库存
        time.setStock(times.get(i -1).getStock() + time.getScheReceipts() - time.getQuanGross() + time.getOutput());
      }
    }
  }
  /**
   * 计划投入
   */
  private void computInput() {
    List<Time> times = mps.getTimes();
    Time time;
    for (int i = 0; i < times.size(); i++) {
      time = times.get(i);
      if (i < (times.size() - mps.getLeadTime())) {
        time.setInput(times.get(i + mps.getLeadTime()).getOutput());
      } else {
        time.setInput(0);
      }
    }
  }
  /**
   * 可供销售
   */
  private void computSales() {
    List<Time> times = mps.getTimes();
    Time time;
    for (int i = 0; i < times.size(); i++) {
      time = times.get(i);
      if (i == 0) {
        time.setSales(time.getStock());
      } else {
        if (time.getOutput() == 0) {
          time.setSales(0);
        } else {
          double temp = time.getOrder();
          for (int j = i + 1; j < times.size(); j++) {
            if (times.get(j).getOutput() != 0) {
              break;
            }
            else {
              temp = temp + times.get(j).getOrder();
            }
          }
          time.setSales(time.getOutput()-temp);
        }
      }
    }
  }
  public mainClass(Mps mps) {
    this.mps = mps;
    computeQuanGross();
    computStock();
    computInput();
    computSales();
  }
  public static void main(String[] args) {
    excelTool tool = new excelTool("C:\\Users\\ZYP\\Desktop\\test2.xls");
    ArrayList<HashMap<String, Double>> info = tool.getInfo();
    ArrayList<String> nameList = tool.getNameList();
    ArrayList<Time> timeList = new ArrayList<Time>();
    Time time;
    int i = 1;
    for (HashMap<String, Double> timeInfo : info) {
      time = new Time();
      time.setTime(i++);
      time.setForecasts(timeInfo.get(nameList.get(0)));
      time.setOrder(timeInfo.get(nameList.get(1)));
      time.setScheReceipts(timeInfo.get(nameList.get(2)));
      time.setQuanGross(timeInfo.get(nameList.get(3)));
      time.setStock(timeInfo.get(nameList.get(4)));
      time.setNetRequire(timeInfo.get(nameList.get(5)));
      time.setInput(timeInfo.get(nameList.get(6)));
      time.setOutput(timeInfo.get(nameList.get(7)));
      time.setSales(timeInfo.get(nameList.get(8)));
      timeList.add(time);
      System.out.println(time);
    }
    System.out.println("\n");
    Mps mps = new Mps(50,1,2,6,50,200);
    mps.setTimes(timeList);
    mainClass test = new mainClass(mps);
    for (Time time1 : timeList) {
      System.out.println(time1);
    }
    ArrayList<HashMap<String, Double>> result = new ArrayList<HashMap<String, Double>>();
    HashMap<String, Double> timeMap;
    for (Time t : timeList) {
      timeMap = new HashMap<String, Double>();
      timeMap.put(nameList.get(0), t.getForecasts());
      timeMap.put(nameList.get(1), t.getOrder());
      timeMap.put(nameList.get(2), t.getScheReceipts());
      timeMap.put(nameList.get(3), t.getQuanGross());
      timeMap.put(nameList.get(4), t.getStock());
      timeMap.put(nameList.get(5), t.getNetRequire());
      timeMap.put(nameList.get(6), t.getInput());
      timeMap.put(nameList.get(7), t.getOutput());
      timeMap.put(nameList.get(8), t.getSales());
      result.add(timeMap);
    }
    tool.createExcel(result);
  }
}
