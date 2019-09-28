package pl.coderslab.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.coderslab.app.exchange.Exchange;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"pl.coderslab.app"}, excludeFilters = {@ComponentScan.Filter(
        type = FilterType.ANNOTATION,
        value = EnableWebMvc.class
)
})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.coderslab.app.repositories")
@EnableScheduling
public class AppConfig {

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName("arbitrazPU");
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(emf);
        return transactionManager;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public Exchange bitbay(){
        Map<String,String> url = new HashMap<>();
        url.put("prefix","https://bitbay.net/API/Public/");
        url.put("suffix","/ticker.json");

        Map<String,String> coins = new HashMap<>();
        coins.put("BTCPLN","BTCPLN");
        coins.put("ETHBTC","ETHBTC");
        coins.put("ETHPLN","ETHPLN");

        Exchange bitbay = new Exchange();
        bitbay.setUrl(url);
        bitbay.setCoins(coins);
        return bitbay;
    }
    @Bean
    public Exchange bittrex(){
        Map<String,String> url = new HashMap<>();

        url.put("prefix", "https://api.bittrex.com/api/v1.1/public/getticker?market=");
        url.put("suffix","");

        Map<String,String> coins = new HashMap<>();
        coins.put("BTCETH","BTC-ETH");

        Exchange bittrex = new Exchange();
        bittrex.setUrl(url);
        bittrex.setCoins(coins);
        return bittrex;
    }
}