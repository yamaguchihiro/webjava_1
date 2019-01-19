package jp.co.systena.tigerscave.ShoppingSite.application.service;

import java.util.ArrayList;
import java.util.List;
import jp.co.systena.tigerscave.ShoppingSite.application.model.Item;

public class ListService {


  //アイテムリスト一覧取得
  //仮にItemクラス内からcreateItemメソッドを呼び出しているが
  //DBから取得して返却するなど処理が変わった場合はItem.java内の該当メソッドを消す
  public static List<Item> getItemList(){

    List<Item> itemlist = new ArrayList<Item>();
    Item keyboard = new Item();
    Item Mouse = new Item();
    Item Monitor = new Item();

    //外部からアイテム一覧を取得する場合ここを書き換える
    keyboard.createItemKeyboard();
    itemlist.add(keyboard);
    Mouse.createItemMouse();;
    itemlist.add(Mouse);
    Monitor.createItemMonitor();
    itemlist.add(Monitor);

    return itemlist;
  }

}
