package pl.coderslab.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.coderslab.app.exchange.Exchange;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;
import java.util.*;

@Configuration
@ComponentScan(basePackages = "pl.coderslab.app")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.coderslab.app.repositories")
@EnableScheduling
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {

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
        String name = "Bitbay";

        Map<String,String> url = new HashMap<>();
        url.put("prefix","https://bitbay.net/API/Public/");
        url.put("suffix","/ticker.json");

        Map<String,String> coins = new HashMap<>();
        coins.put("BTCPLN","BTCPLN");
        coins.put("BTCETH","ETHBTC");
        coins.put("ETHPLN","ETHPLN");

        List<String> coinsName = new ArrayList<>();
        coinsName.add("BTCPLN");
        coinsName.add("BTCETH");
        coinsName.add("ETHPLN");

        Double feeProcentMaker = 0.0;

        Map<String, Double> feesWithdrawCoin = new HashMap<>();
        feesWithdrawCoin.put("ETHBTC", 0.00045);
//        feesWithdrawCoin.put("")

        Exchange bitbay = new Exchange();
        bitbay.setName(name);
        bitbay.setUrl(url);
        bitbay.setCoins(coins);
        bitbay.setCoinsName(coinsName);
        return bitbay;
    }
    @Bean
    public Exchange bittrex(){
        String name = "Bittrex";

        Map<String,String> url = new HashMap<>();

        url.put("prefix", "https://api.bittrex.com/api/v1.1/public/getticker?market=");
        url.put("suffix","");

        Map<String,String> coins = new HashMap<>();
        coins.put("BTCETH","BTC-ETH");

        List<String> coinsName = new ArrayList<>();
        coinsName.add("BTCETH");
        coinsName.add("BTCPLN");
        coinsName.add("ETHPLN");

        Double feePorcentMaker = 0.25;

        Exchange bittrex = new Exchange();
        bittrex.setName(name);
        bittrex.setUrl(url);
        bittrex.setCoins(coins);
        return bittrex;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public LocaleContextResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pl", "PL"));
        return localeResolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}