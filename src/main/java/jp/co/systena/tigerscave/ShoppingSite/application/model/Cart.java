package jp.co.systena.tigerscave.ShoppingSite.application.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
  private List<Order> orderlist = new ArrayList<Order>();

  public List<Order> getOrderlist() {
    return orderlist;
  }

  public void setOrderlist(List<Order> orderlist) {
    this.orderlist = orderlist;
  }

  public void addOrderlist(Order order) {
    this.orderlist.add(order);
  }

  public int calOrderTotalPrice() {
    int cal = 0;
    for(Order o : this.orderlist) {
       cal += o.calOrderPrice();
    }
    return cal;
  }

}
