import java.io.*;
import java.nio.file.Paths;

/**
 * Created by thomas cauwelier on 09-Mar-17.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String classPath;
        if (args.length >= 1) {
            classPath = args[0];
        } else {
            classPath = Paths.get("./").toString();
        }
        System.out.println("Path to folder containing class files = " + classPath);
        String classFile = "MyProcess";

        ProcessHandler process1 = new ProcessHandler("java", "-cp", classPath, classFile, "Process1");
        ProcessHandler process2 = new ProcessHandler("java", "-cp", classPath, classFile, "Process2");
        ProcessHandler process3 = new ProcessHandler("java", "-cp", classPath, classFile, "Process3");

        process1.send("Hello");
        process2.send(process1.receive());
        process3.send(process2.receive());
        process2.send(process3.receive());
        process1.send(process2.receive());
        System.out.println(process1.receive());
    }
}

class ProcessHandler {
    private BufferedReader reader;
    private BufferedWriter writer;

    ProcessHandler(String... command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    }

    public void send(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }

    public String receive() throws IOException {
        return reader.readLine();
    }
}
