import java.io.*;

/**
 * Created by thomas cauwelier on 09-Mar-17.
 */
public class MyProcess {
    public static void main(String[] args) {
        String myName;
        if (args.length >= 1) {
            myName = args[0];
        } else {
            myName = "MyProcess";
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            try {
                String message = reader.readLine();
                if (message == null || message.isEmpty() || message.equals("stop")) {
                    break;
                } else {
                    writer.write(message + " from " + myName);
                    writer.newLine();
                    writer.flush();
                }
            } catch (IOException ignored) {
            }
        }

    }
}
