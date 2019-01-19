package jp.co.systena.tigerscave.ShoppingSite.application.model;

public class Item {
  private String name;
  private int price;

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }

  //ListServiceで呼び出すアイテム種類の列記
  //のちにListServiseでデータベースからアイテム種類を呼び出したりする改修が入った場合
  //この部分は丸ごと消す
  public void createItemKeyboard() {
    this.name = "キーボード";
    this.price = 2000;
  }

  public void createItemMouse() {
    this.name = "マウス";
    this.price = 500;
  }

  public void createItemMonitor() {
    this.name = "モニター";
    this.price = 10000;
    }

}
