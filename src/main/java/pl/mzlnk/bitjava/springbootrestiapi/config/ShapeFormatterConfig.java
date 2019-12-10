package pl.mzlnk.bitjava.springbootrestiapi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mzlnk.bitjava.springbootrestiapi.model.formatter.ShapeFormatter;
import pl.mzlnk.bitjava.springbootrestiapi.model.formatter.SquareFormatter;
import pl.mzlnk.bitjava.springbootrestiapi.model.formatter.TriangleFormatter;

@Configuration
public class ShapeFormatterConfig {

    @Bean
    @Qualifier("triangleFormatter")
    public ShapeFormatter triangleFormatter() {
        return new TriangleFormatter();
    }

    @Bean
    @Qualifier("squareFormatter")
    public ShapeFormatter squareFormatter() {
        return new SquareFormatter();
    }

}
