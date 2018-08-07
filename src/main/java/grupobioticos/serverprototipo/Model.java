package grupobioticos.serverprototipo;

import java.io.IOException;

public class Model {

    private CommandsConfiguration configuration;

    private static final String MENSAJE_LECTURA_INFORMACION = "Lectura de Información realizada. Salida almacenada en %s y %s";
    private static final String MENSAJE_BUSQUEDA_COINCIDENCIAS = "Búsqueda de Coincidencias realizada. Salida almacenada en %s";
    private static final String MENSAJE_CONSTRUCCION_ENSAMBLES = "Construcción de Ensambles realizada. Salida almacenada en %s";
    private static final String MENSAJE_GENERACION_INFORMES = "Generación de Informes realizada a partir del archivo %s";

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

        String comandoEjecutar = this.configuration.getSubgrupo_1();

        comandoEjecutar = !readsIn1.equals("undefined") && !readsIn1.equals("") ? comandoEjecutar.replace("genomas/A5-1.fq", readsIn1) : comandoEjecutar;
        comandoEjecutar = !readsIn2.equals("undefined") && !readsIn2.equals("") ? comandoEjecutar.replace("genomas/A5-2.fq", readsIn2) : comandoEjecutar;
        comandoEjecutar = !readsOut1.equals("undefined") && !readsOut1.equals("") ? comandoEjecutar.replace("output_forward_paired.fq", readsOut1) : comandoEjecutar;
        comandoEjecutar = !readsOut2.equals("undefined") && !readsOut2.equals("") ? comandoEjecutar.replace("output_reverse_paired.fq", readsOut2) : comandoEjecutar;

        runCommand(comandoEjecutar);

        if ((!readsOut1.equals("undefined") && !readsOut1.equals("")) || (!readsOut2.equals("undefined") && !readsOut2.equals("")))
            return String.format(MENSAJE_LECTURA_INFORMACION, readsOut1, readsOut2);

        return String.format(MENSAJE_LECTURA_INFORMACION, "output_forward_paired.fq", "output_reverse_paired.fq");
    }

    public String busquedaCoincidencias(
            String archivoReferencia,
            String genomaAnalizar,
            String archivoSalida
    ) {
        System.out.printf("%s. Parámetros -> %s, %s, %s\n", "Búsqueda de Coincidencias", archivoReferencia, genomaAnalizar, archivoSalida);

        String comando_1 = this.configuration.getSubgrupo_2_1();
        String comando_2 = this.configuration.getSubgrupo_2_2();
        String comando_3 = this.configuration.getSubgrupo_2_3();

        comando_1 = (!archivoReferencia.equals("undefined") && !archivoReferencia.equals("")) ? comando_1.replace("mrna.fa", archivoReferencia) : comando_1;
        comando_3 = (!genomaAnalizar.equals("undefined") && !genomaAnalizar.equals(""))? comando_3.replace("CRR019982_f1.fq", genomaAnalizar) : comando_3;
        comando_3 = (!archivoSalida.equals("undefined") && !genomaAnalizar.equals(""))? comando_3.replace("salida.sam", archivoSalida) : comando_3;

        try {
            Process p = Runtime.getRuntime().exec(comando_1);

            p = Runtime.getRuntime().exec(comando_2);

            p = Runtime.getRuntime().exec(comando_3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (archivoSalida.equals("undefined") || archivoSalida.equals(""))
            return String.format(MENSAJE_BUSQUEDA_COINCIDENCIAS, "salida.sam");

        return String.format(MENSAJE_BUSQUEDA_COINCIDENCIAS, archivoSalida);
    }

    public String construccionEnsambles(
            String archivoEntrada,
            String opcionEnsamble,
            String archivoSalida
    ) {
        String comandoEjecutar = this.configuration.getSubgrupo_3();

        comandoEjecutar = !archivoEntrada.equals("undefined") ? comandoEjecutar.replace("examples/AlligatorMito.fa", archivoEntrada) : comandoEjecutar;
        comandoEjecutar = opcionEnsamble.equals("Paired-End") ? comandoEjecutar.replace("--mate_pair ", "") : comandoEjecutar;
        comandoEjecutar = !archivoSalida.equals("undefined") ? comandoEjecutar.replace("out.sam", archivoSalida) :  comandoEjecutar;

        System.out.printf("%s. Parámetros -> %s, %s, %s\n", "Construcción de Ensambles", archivoEntrada, opcionEnsamble, archivoSalida);
        runCommand(comandoEjecutar);

        if (archivoSalida.equals("undefined") || archivoSalida.equals(""))
            return String.format(MENSAJE_CONSTRUCCION_ENSAMBLES, "out.sam");

        return String.format(MENSAJE_CONSTRUCCION_ENSAMBLES, archivoSalida);
    }

    public String generacionInformes(
            String archivo1,
            String archivo2,
            String archivo3
    ) {
        System.out.printf("%s. Parámetros -> %s, %s, %s\n", "Generación de Informes", archivo1, archivo2, archivo3);

        String comandoEjecutar = this.configuration.getSubgrupo_4();

        comandoEjecutar = !archivo1.equals("undefined") ? comandoEjecutar.replace("./genomas/A5-1.fq", archivo1) : comandoEjecutar;
        comandoEjecutar = !archivo2.equals("undefined") ? comandoEjecutar + " " + archivo2 : comandoEjecutar;
        comandoEjecutar = !archivo3.equals("undefined") ? comandoEjecutar + " " + archivo3 : comandoEjecutar;

        runCommand(comandoEjecutar);

        if (archivo3.equals("undefined") || archivo3.equals(""))
            return String.format(MENSAJE_GENERACION_INFORMES, "./genomas/A5-1.fq");

        return String.format(MENSAJE_GENERACION_INFORMES, archivo1);
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
