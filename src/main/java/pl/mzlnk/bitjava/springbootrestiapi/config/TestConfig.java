package pl.mzlnk.bitjava.springbootrestiapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mzlnk.bitjava.springbootrestiapi.model.formatter.ShapeFormatter;
import pl.mzlnk.bitjava.springbootrestiapi.model.formatter.TriangleFormatter;

@Configuration
public class TestConfig {

    @Autowired
    @Qualifier("squareFormatter")
    private ShapeFormatter shapeFormatter;

    @Bean
    public String string() {
        shapeFormatter.format();
        return null;
    }

}
