package com.charlotte.kies.configuration;

import com.charlotte.kies.User;
import com.charlotte.kies.repository.UserRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryDependsOnPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@EntityScan(basePackageClasses = User.class)
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
//@EnableAutoConfiguration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Config implements WebMvcConfigurer {



        private final long MAX_AGE_SECS = 3600;

        // variable defined in application.properties
//    @Value("${app.cors.allowedOrigins}")
//    private String[] allowedOrigins;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
//                .allowCredentials(false)
                    .maxAge(MAX_AGE_SECS);
        }


        // This seems to get rid of the CORS pre-flight error.
        @Configuration
        public class CorsConfig implements WebMvcConfigurer {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                        .allowCredentials(true)
                ;
            }


        }

    @Configuration
    class PersistenceContext {

        @Bean(destroyMethod = "close")
        DataSource dataSource(Environment env) {
            HikariConfig dataSourceConfig = new HikariConfig();
//            dataSourceConfig.setDriverClassName("org.hibernate.dialect.PostgreSQLDialect");
            dataSourceConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/lobster");
            dataSourceConfig.setUsername("postgres");
            dataSourceConfig.setPassword("postgres1");

            return new HikariDataSource(dataSourceConfig);
        }
    }


}

