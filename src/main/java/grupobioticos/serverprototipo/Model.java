package grupobioticos.serverprototipo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Model {

    private CommandsConfiguration configuration;

    public Model(CommandsConfiguration configuration) {
        this.configuration = configuration;
    }

    public String lecturaInformacion(
            String readsIn1,
            String readsIn2,
            String readsOut1,
            String readsOut2
    ) {
        System.out.printf("%s. Parámetros -> %s, %s, %s, %s\n", "Lectura de Información", readsIn1, readsIn2, readsOut1, readsOut2);
        runCommand(this.configuration.getSubgrupo_1());
        return "Lectura de Información realizada";
    }

    public String busquedaCoincidencias(
            String archivoReferencia,
            String genomaAnalizar,
            String archivoSalida
    ) {
        System.out.printf("%s. Parámetros -> %s, %s, %s\n", "Búsqueda de Coincidencias", archivoReferencia, genomaAnalizar, archivoSalida);
//        runCommand(this.configuration.getSubgrupo_2());

        try {
            String line;
            Process p = Runtime.getRuntime().exec(this.configuration.getSubgrupo_2_1());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null)
                System.out.println(line);

            input.close();

            p = Runtime.getRuntime().exec(this.configuration.getSubgrupo_2_2());
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null)
                System.out.println(line);

            input.close();

            p = Runtime.getRuntime().exec(this.configuration.getSubgrupo_2_3());
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null)
                System.out.println(line);

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Búsqueda de Coincidencias realizada";
    }

    public String construccionEnsambles(
            String archivoEntrada,
            String opcionEnsamble,
            String archivoSalida
    ) {
        System.out.printf("%s. Parámetros -> %s, %s, %s\n", "Construcción de Ensambles", archivoEntrada, opcionEnsamble, archivoSalida);
        runCommand(this.configuration.getSubgrupo_3());
        return "Construcción de Ensambles realizada";
    }

    public String generacionInformes(
            String archivo1,
            String archivo2,
            String archivo3
    ) {
        System.out.printf("%s. Parámetros -> %s, %s, %s\n", "Generación de Informes", archivo1, archivo2, archivo3);
        runCommand(this.configuration.getSubgrupo_4());
        return "Generación de Informes realizada";
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
