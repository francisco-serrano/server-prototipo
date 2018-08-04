package grupobioticos.serverprototipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://client-prototipo.herokuapp.com",
        "http://client-prototipo.herokuapp.com/",
        "client-prototipo.herokuapp.com/"
})
public class Controller {

    private CommandsConfiguration configuration;
    private boolean runningLocal;

    @Autowired
    public void setConfiguration(CommandsConfiguration configuration) {
        this.configuration = configuration;
    }

    @Autowired
    public void setPort(Environment environment) {
        int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port")));
        this.runningLocal = port == 8080;
    }

    @GetMapping(value = "/")
    public String prueba() {
        return "respuesta de prueba de la api";
    }

    @GetMapping(value = "/lecturainformacion")
    public String lecturaInformacion() {
        System.out.println("Llegó request de LECTURA INFORMACION");

        if (runningLocal)
            runCommand(this.configuration.getSubgrupo_1());

        return String.format("lectura de información - runningLocal: %s", this.runningLocal);
    }

    @GetMapping(value = "/busquedacoincidencias")
    public String busquedaCoincidencias() {
        System.out.println("Llegó request de BUSQUEDA COINCIDENCIAS");

        if (runningLocal)
            runCommand(this.configuration.getSubgrupo_2());

        return String.format("búsqueda de coincidencias - runningLocal: %s", this.runningLocal);
    }

    @GetMapping(value = "/construccionensambles")
    public String construccionEnsambles(
            @RequestParam(value="archivo_entrada") String archivoEntrada,
            @RequestParam(value="opcion_ensamble") String opcionEnsamble,
            @RequestParam(value="archivo_salida") String archivoSalida
    ) {
        System.out.println("Llegó request de CONSTRUCCION ENSAMBLES");
        System.out.printf("PARAMETROS RECIBIDOS -> %s, %s, %s\n", archivoEntrada, opcionEnsamble, archivoSalida);

        if (runningLocal)
            runCommand(this.configuration.getSubgrupo_3());

        return String.format("construcción de ensambles - runningLocal: %s", this.runningLocal);
    }

    @GetMapping(value = "/generacioninformes")
    public String generacionInformes() {
        System.out.println("Llegó request de GENERACION INFORMES");

        if (runningLocal)
            runCommand(this.configuration.getSubgrupo_4());

        return String.format("generacion de informes - runningLocal: %s", this.runningLocal);
    }

    private void runCommand(String command) {
        System.out.println("COMANDO A EJECUTAR: " + command);

        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
