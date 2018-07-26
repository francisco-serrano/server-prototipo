package grupobioticos.serverprototipo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/plain")
    public String prueba() {
        return "respuesta de prueba de la api";
    }

    @RequestMapping(value = "/lecturainformacion", method = RequestMethod.GET, produces = "text/plain")
    public String lecturaInformacion() {
        System.out.println("Llegó request de LECTURA INFORMACION");
        return "lectura de información";
    }

    @RequestMapping(value = "/busquedacoincidencias", method = RequestMethod.GET, produces = "text/plain")
    public String busquedaCoincidencias() {
        System.out.println("Llegó request de BUSQUEDA COINCIDENCIAS");
        return "búsqueda de coincidencias";
    }

    @RequestMapping(value = "/construccionensambles", method = RequestMethod.GET, produces = "text/plain")
    public String construccionEnsambles() {
        System.out.println("Llegó request de CONSTRUCCION ENSAMBLES");
        return "construcción de ensambles";
    }

    @RequestMapping(value = "/generacioninformes", method = RequestMethod.GET, produces = "text/plain")
    public String generacionInformes() {
        System.out.println("Llegó request de GENERACION INFORMES");
        return "generacion de informes";
    }

}
