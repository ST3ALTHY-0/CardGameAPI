package ST3ALTHY0.CardGameAPI.restservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {
    // im not sure what this bean is really doing, but it is needed else i get weird
    // errors like:
    // Error creating bean with name 'entityManagerFactory' defined in class path
    // resource
    // [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]:
    // [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested
    // exception is org.hibernate.tool.schema.spi.SchemaManagementException:
    // Schema-validation: missing table [games]

    //error is weird because nowhere in my code is there a 'games' Table, only a 'Games' Table


    //error might be because of a conflict between some Jakarta and Hibernate dependencies



    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
            DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = builder
                .dataSource(dataSource)
                .packages("ST3ALTHY0.CardGameAPI.restservice")
                .persistenceUnit("default")
                .build();
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);
        return factoryBean;
    }
}