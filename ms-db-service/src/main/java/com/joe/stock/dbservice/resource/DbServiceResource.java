package com.joe.stock.dbservice.resource;

import com.joe.stock.dbservice.model.Quote;
import com.joe.stock.dbservice.model.Quotes;
import com.joe.stock.dbservice.repository.QuotesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    private QuotesRepository quotesRespository;

    public DbServiceResource(QuotesRepository quotesRespository) {
        this.quotesRespository = quotesRespository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(
            @PathVariable("username") final String username){
        return getQuotesByUserName(username);
    }


    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){
        quotes.getQuotes().stream()
                .map(q-> new Quote(quotes.getUserName(),q))
                .forEach(q-> quotesRespository.save(q));
        return getQuotesByUserName(quotes.getUserName());
    }


    private List<String> getQuotesByUserName(String userName){
        return quotesRespository.findByUserName(userName)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }


    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String userName){
        List<Quote> quotes = quotesRespository.findByUserName(userName);
        quotesRespository.deleteAll(quotes);
        return getQuotesByUserName(userName);
    }

}
