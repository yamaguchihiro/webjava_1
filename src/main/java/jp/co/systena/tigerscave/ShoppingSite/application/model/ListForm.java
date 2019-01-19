package jp.co.systena.tigerscave.ShoppingSite.application.model;

import javax.validation.constraints.Min;

public class ListForm {

  @Min(1)
  private int num;
  private Item item;

  public int getNum() {
    return num;
  }
  public void setNum(int num) {
    this.num = num;
  }
  public Item getItem() {
    return item;
  }
  public void setItem(Item item) {
    this.item = item;
  }
}
