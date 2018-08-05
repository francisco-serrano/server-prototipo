package grupobioticos.serverprototipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://client-prototipo.herokuapp.com",
        "http://client-prototipo.herokuapp.com/",
        "client-prototipo.herokuapp.com/"
})
public class Controller {

    private boolean runningLocal;
    private Model model;

    @Autowired
    public void setConfiguration(CommandsConfiguration configuration) {
        this.model = new Model(configuration);
    }

    @Autowired
    public void setPort(Environment environment) {
        int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port")));
        this.runningLocal = (port == 8080);
    }

    @GetMapping(value = "/")
    public String prueba() {
        return "respuesta de prueba de la api";
    }

    @GetMapping(value = "/lecturainformacion", produces = "application/json")
    public Map<String, Object> lecturaInformacion(
            @RequestParam(value = "reads_in_1") String readsIn1,
            @RequestParam(value = "reads_in_2") String readsIn2,
            @RequestParam(value = "reads_out_1") String readsOut1,
            @RequestParam(value = "reads_out_2") String readsOut2

    ) {
        System.out.println("Llegó request de LECTURA INFORMACION");

        String resultado = "Lectura de Información en la nube no implementada";

        if (runningLocal)
            resultado = model.lecturaInformacion(readsIn1, readsIn2, readsOut1, readsOut2);

        return generateOutput(resultado, runningLocal);
    }

    @GetMapping(value = "/busquedacoincidencias", produces = "application/json")
    public Map<String, Object> busquedaCoincidencias(
            @RequestParam(value = "archivo_referencia") String archivoReferencia,
            @RequestParam(value = "genoma_analizar") String genomaAnalizar,
            @RequestParam(value = "archivo_salida") String archivoSalida
    ) {
        System.out.println("Llegó request de BUSQUEDA COINCIDENCIAS");
        String resultado = "Búsqueda de Coincidencias en la nube no implementada";

        if (runningLocal)
            resultado = model.busquedaCoincidencias(archivoReferencia, genomaAnalizar, archivoSalida);

        return generateOutput(resultado, runningLocal);
    }

    @GetMapping(value = "/construccionensambles", produces = "application/json")
    public Map<String, Object> construccionEnsambles(
            @RequestParam(value = "archivo_entrada") String archivoEntrada,
            @RequestParam(value = "opcion_ensamble") String opcionEnsamble,
            @RequestParam(value = "archivo_salida") String archivoSalida
    ) {
        System.out.println("Llegó request de CONSTRUCCION ENSAMBLES");
        String resultado = "Construcción de Ensambles en la nube no implementada";

        if (runningLocal)
            resultado = model.construccionEnsambles(archivoEntrada, opcionEnsamble, archivoSalida);

        return generateOutput(resultado, runningLocal);
    }

    @GetMapping(value = "/generacioninformes", produces = "application/json")
    public Map<String, Object> generacionInformes(
            @RequestParam(value = "archivo_1") String archivo1,
            @RequestParam(value = "archivo_2") String archivo2,
            @RequestParam(value = "archivo_3") String archivo3

    ) {
        System.out.println("Llegó request de GENERACION INFORMES");
        String resultado = "Generación de Informes en la nube no implementada";

        if (runningLocal)
            resultado = model.generacionInformes(archivo1, archivo2, archivo3);

        return generateOutput(resultado, runningLocal);
    }

    private Map<String, Object> generateOutput(String message, boolean runningLocal) {
        Map<String, Object> jsonRetornar = new HashMap<>();
        jsonRetornar.put("mensaje", message);
        jsonRetornar.put("runningLocal", runningLocal);

        return jsonRetornar;
    }

    private Map<String, Object> generateOutput(String message, boolean runningLocal, Map<String, Object> additionalData) {
        Map<String, Object> jsonRetornar = generateOutput(message, runningLocal);
        jsonRetornar.put("Información Adicional", additionalData);

        return jsonRetornar;
    }

}
