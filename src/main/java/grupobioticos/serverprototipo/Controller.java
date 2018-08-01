package grupobioticos.serverprototipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://client-prototipo.herokuapp.com",
        "http://client-prototipo.herokuapp.com/"
})
public class Controller {

    private CommandsConfiguration configuration;
    private int port;

    @Autowired
    public void setConfiguration(CommandsConfiguration configuration) {
        this.configuration = configuration;
    }

    @Autowired
    public void setPort(Environment environment) {
        this.port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port")));
    }

    @GetMapping(value = "/")
    public String prueba() {
        return "respuesta de prueba de la api";
    }

    @GetMapping(value = "/lecturainformacion")
    public String lecturaInformacion() {
        System.out.println("Llegó request de LECTURA INFORMACION");
        System.out.println("COMANDO A EJECUTAR: " + this.configuration.getSubgrupo_1());

        String modoEjecucion = this.port == 8080 ? "LOCAL" : "REMOTE";

        return String.format("lectura de información %s", modoEjecucion);
    }

    @GetMapping(value = "/busquedacoincidencias")
    public String busquedaCoincidencias() {
        System.out.println("Llegó request de BUSQUEDA COINCIDENCIAS");
        System.out.println("COMANDO A EJECUTAR: " + this.configuration.getSubgrupo_2());

        String modoEjecucion = this.port == 8080 ? "LOCAL" : "REMOTE";

        return String.format("búsqueda de coincidencias %s", modoEjecucion);
    }

    @GetMapping(value = "/construccionensambles")
    public String construccionEnsambles() {
        System.out.println("Llegó request de CONSTRUCCION ENSAMBLES");
        System.out.println("COMANDO A EJECUTAR: " + this.configuration.getSubgrupo_3());

        String modoEjecucion = this.port == 8080 ? "LOCAL" : "REMOTE";

        return String.format("construcción de ensambles %s", modoEjecucion);
    }

    @GetMapping(value = "/generacioninformes")
    public String generacionInformes() {
        System.out.println("Llegó request de GENERACION INFORMES");
        System.out.println("COMANDO A EJECUTAR: " + this.configuration.getSubgrupo_4());

        String modoEjecucion = this.port == 8080 ? "LOCAL" : "REMOTE";

        return String.format("generacion de informes %s", modoEjecucion);
    }

    private void introducirRetardo(int cantidadSegundos) {
        try {
            Thread.sleep(cantidadSegundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
