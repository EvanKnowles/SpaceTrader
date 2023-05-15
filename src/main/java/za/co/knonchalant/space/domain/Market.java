
package za.co.knonchalant.space.domain;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Market {

    @Expose
    private List<Exchange> exchange;
    @Expose
    private List<Export> exports;
    @Expose
    private List<Import> imports;
    @Expose
    private String symbol;
    @Expose
    private List<TradeGood> tradeGoods;
    @Expose
    private List<Transaction> transactions;

    public List<Exchange> getExchange() {
        return exchange;
    }

    public void setExchange(List<Exchange> exchange) {
        this.exchange = exchange;
    }

    public List<Export> getExports() {
        return exports;
    }

    public void setExports(List<Export> exports) {
        this.exports = exports;
    }

    public List<Import> getImports() {
        return imports;
    }

    public void setImports(List<Import> imports) {
        this.imports = imports;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<TradeGood> getTradeGoods() {
        return tradeGoods;
    }

    public void setTradeGoods(List<TradeGood> tradeGoods) {
        this.tradeGoods = tradeGoods;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
