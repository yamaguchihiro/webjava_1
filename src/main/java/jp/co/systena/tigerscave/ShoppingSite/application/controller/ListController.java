package jp.co.systena.tigerscave.ShoppingSite.application.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.ShoppingSite.application.model.Cart;
import jp.co.systena.tigerscave.ShoppingSite.application.model.Item;
import jp.co.systena.tigerscave.ShoppingSite.application.model.ListForm;
import jp.co.systena.tigerscave.ShoppingSite.application.model.Order;
import jp.co.systena.tigerscave.ShoppingSite.application.service.ListService;


@Controller // Viewあり。Viewを返却するアノテーション

public class ListController {
  @Autowired
  HttpSession session;

  @RequestMapping(value = {"/ShoppingSite"}, method = {RequestMethod.GET}) // URLとのマッピング

  public ModelAndView show(ModelAndView mav) {
    // Viewに渡すデータを設定
    // セッション情報から保存したデータを取得してメッセージを生成
    ListForm userForm = (ListForm) session.getAttribute("form");
    session.removeAttribute("form");
    if (userForm != null) {
      mav.addObject("message", userForm.getItem().getName()+"を購入しました。単価は"+userForm.getItem().getPrice()+"円です。購入個数は"+userForm.getNum()+"個です。");
    }

    List<Item> itemlist = ListService.getItemList();

    Cart cart = (Cart) session.getAttribute("CurrentCart");

    if( cart == null) {
      cart = new Cart();
      session.setAttribute("CurrentCart", cart);
    }

    mav.addObject(cart);
    mav.addObject("TotalPrice",cart.calOrderTotalPrice());
    mav.addObject("ListForm", new ListForm());  // 新規クラスを設定
    mav.addObject("itemlist", itemlist);

    BindingResult bindingResult = (BindingResult) session.getAttribute("result");
    if (bindingResult != null) {
      mav.addObject("bindingResult", bindingResult);
    }
    // Viewのテンプレート名を設定
    mav.setViewName("ListView");
    return mav;
  }

  @RequestMapping(value="/ShoppingSite", method = RequestMethod.POST)  // URLとのマッピング
  private ModelAndView order(ModelAndView mav, @Valid ListForm ListForm, BindingResult bindingResult, HttpServletRequest request) {

    Cart cart = (Cart) session.getAttribute("CurrentCart");

    if( cart == null) {
      cart = new Cart();
        session.setAttribute("CurrentCart", cart);
    }

    if (bindingResult.getAllErrors().size() > 0) {

      System.out.println(bindingResult.getAllErrors().toString());

      // エラーがある場合はそのまま戻す
      mav.addObject("ListForm", new ListForm());  // 新規クラスを設定
      List<Item> itemlist = ListService.getItemList();
      mav.addObject("itemlist", itemlist);
      // Viewのテンプレート名を設定
      return new ModelAndView("redirect:/ShoppingSite");
    }

    Order order = new Order();
    order.setItem(ListForm.getItem());
    order.setNum(ListForm.getNum());
    cart.addOrderlist(order);
    // データをセッションへ保存
    session.setAttribute("form", ListForm);
    session.setAttribute("CurrentCart", cart);
    return new ModelAndView("redirect:/ShoppingSite");        // リダイレクト
  }
}
