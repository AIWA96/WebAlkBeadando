package hu.iit.uni.miskolc.webalk.web.config;

import hu.iit.uni.miskolc.webalk.core.service.AccessoriesService;
import hu.iit.uni.miskolc.webalk.dao.AccessoriesDAOsql;
import hu.iit.uni.miskolc.webalk.service.AccessoriesServiceImpl;
import hu.iit.uni.miskolc.webalk.service.dao.AccessoriesDAO;
import hu.uni.miskolc.webalk.controller.AccessoriesController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public AccessoriesDAO accessoriesDAO() {
        return new AccessoriesDAOsql();
    }

    @Bean
    public AccessoriesService accessoriesService() {
        return new AccessoriesServiceImpl(accessoriesDAO());
    }

    @Bean
    public AccessoriesController accessoriesController() {
        return new AccessoriesController(accessoriesService());
    }

   /* @Bean
    public ShopDAO shopDAO(){
        return new ShopDAOsql();
    }

    @Bean
    public ShopService shopService(){
        return new ShopServiceImpl(shopDAO());
    }

    @Bean
    public ShopController shopController(){
        return new ShopController(shopService());
    }

    @Bean
    public EmployeeDAO employeeDAO(){
        return new EmployeeDAOsql();
    }

    @Bean
    public EmployeeService employeeService(){
        return new EmployeeServiceImpl(employeeDAO());
    }

    @Bean
    public EmployeeController employeeController(){
        return new EmployeeController(employeeService());
    }

    @Bean
    public GlassesDAO glassesDAO(){
        return new GlassesDAOsql();
    }

    @Bean
    public GlassesService glassesService(){
        return new GlassesServiceImpl(glassesDAO());
    }

    @Bean
    public GlassesController glassesController(){
        return GlassesController(glassesService());
    }
    */
}
