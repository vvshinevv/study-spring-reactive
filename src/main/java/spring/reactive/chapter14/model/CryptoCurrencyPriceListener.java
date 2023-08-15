package spring.reactive.chapter14.model;

import java.util.List;

public interface CryptoCurrencyPriceListener {
    void onPrice(List<Integer> priceList);
    void onComplete();
}
