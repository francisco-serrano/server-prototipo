package grupobioticos.serverprototipo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:comandos.properties")
@ConfigurationProperties
public class CommandsConfiguration {

    @Value("${subgrupo_1}")
    private String subgrupo_1;

    @Value("${subgrupo_2}")
    private String subgrupo_2;

    @Value("${subgrupo_2_1}")
    private String subgrupo_2_1;

    @Value("${subgrupo_2_2}")
    private String subgrupo_2_2;

    @Value("${subgrupo_2_3}")
    private String subgrupo_2_3;

    @Value("${subgrupo_3}")
    private String subgrupo_3;

    @Value("${subgrupo_4}")
    private String subgrupo_4;

    public String getSubgrupo_1() {
        return subgrupo_1;
    }

    public String getSubgrupo_2() {
        return subgrupo_2;
    }

    public String getSubgrupo_3() {
        return subgrupo_3;
    }

    public String getSubgrupo_4() {
        return subgrupo_4;
    }
}
