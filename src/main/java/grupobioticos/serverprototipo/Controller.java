package grupobioticos.serverprototipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://client-prototipo.herokuapp.com"})
public class Controller {

    private CommandsConfiguration configuration;

    @Autowired
    Environment environment;

    @Autowired
    public void setConfiguration(CommandsConfiguration configuration) {
        this.configuration = configuration;
    }

    @GetMapping(value = "/")
    public String prueba() {
        System.out.println("PORT: " + environment.getProperty("local.server.port"));

        return "respuesta de prueba de la api";
    }

    @GetMapping(value = "/lecturainformacion")
    public String lecturaInformacion() {
        System.out.println("Llegó request de LECTURA INFORMACION");

//        int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port")));

        System.out.println("COMANDO A EJECUTAR: " + this.configuration.getSubgrupo_1());

//        introducirRetardo(1);

        return "lectura de información";
    }

    @GetMapping(value = "/busquedacoincidencias")
    public String busquedaCoincidencias() {
        System.out.println("Llegó request de BUSQUEDA COINCIDENCIAS");

        introducirRetardo(1);

        return "búsqueda de coincidencias";
    }

    @GetMapping(value = "/construccionensambles")
    public String construccionEnsambles() {
        System.out.println("Llegó request de CONSTRUCCION ENSAMBLES");

        introducirRetardo(1);

        return "construcción de ensambles";
    }

    @GetMapping(value = "/generacioninformes")
    public String generacionInformes() {
        System.out.println("Llegó request de GENERACION INFORMES");

        introducirRetardo(1);

        return "generacion de informes";
    }

    private void introducirRetardo(int cantidadSegundos) {
        try {
            Thread.sleep(cantidadSegundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
