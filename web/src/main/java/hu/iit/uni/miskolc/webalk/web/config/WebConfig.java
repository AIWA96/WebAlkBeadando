package hu.iit.uni.miskolc.webalk.web.config;

import hu.iit.uni.miskolc.webalk.controller.AccessoriesController;
import hu.iit.uni.miskolc.webalk.controller.EmployeeController;
import hu.iit.uni.miskolc.webalk.controller.GlassesController;
import hu.iit.uni.miskolc.webalk.controller.ShopController;
import hu.iit.uni.miskolc.webalk.core.service.AccessoriesService;
import hu.iit.uni.miskolc.webalk.core.service.EmployeeService;
import hu.iit.uni.miskolc.webalk.core.service.GlassesService;
import hu.iit.uni.miskolc.webalk.core.service.ShopService;
import hu.iit.uni.miskolc.webalk.dao.AccessoriesDAOsql;
import hu.iit.uni.miskolc.webalk.dao.EmployeeDAOsql;
import hu.iit.uni.miskolc.webalk.dao.GlassesDAOsql;
import hu.iit.uni.miskolc.webalk.dao.ShopDAOsql;
import hu.iit.uni.miskolc.webalk.service.AccessoriesServiceImpl;
import hu.iit.uni.miskolc.webalk.service.EmployeeServiceImpl;
import hu.iit.uni.miskolc.webalk.service.GlassesServiceImpl;
import hu.iit.uni.miskolc.webalk.service.ShopServiceImpl;
import hu.iit.uni.miskolc.webalk.service.dao.AccessoriesDAO;
import hu.iit.uni.miskolc.webalk.service.dao.EmployeeDAO;
import hu.iit.uni.miskolc.webalk.service.dao.GlassesDAO;
import hu.iit.uni.miskolc.webalk.service.dao.ShopDAO;
import hu.iit.uni.miskolc.webalk.service.dao.exceptions.CreateDataBaseException;
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
    public AccessoriesController accessoriesController() throws CreateDataBaseException {
        return new AccessoriesController(accessoriesService());
    }

    @Bean
    public ShopDAO shopDAO() {
        return new ShopDAOsql();
    }

    @Bean
    public ShopService shopService() {
        return new ShopServiceImpl(shopDAO());
    }

    @Bean
    public ShopController shopController() throws CreateDataBaseException {
        return new ShopController(shopService());
    }

    @Bean
    public EmployeeDAO employeeDAO() {
        return new EmployeeDAOsql();
    }

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl(employeeDAO());
    }

    @Bean
    public EmployeeController employeeController() throws CreateDataBaseException {
        return new EmployeeController(employeeService());
    }

    @Bean
    public GlassesDAO glassesDAO() {
        return new GlassesDAOsql();
    }

    @Bean
    public GlassesService glassesService() {
        return new GlassesServiceImpl(glassesDAO());
    }

    @Bean
    public GlassesController glassesController() throws CreateDataBaseException {
        return new GlassesController(glassesService());
    }
}
