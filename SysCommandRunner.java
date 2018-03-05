import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SysCommandRunner {

    public static void main(String args[]) throws IOException {

        SysCommandRunner m1 = new SysCommandRunner();

        String url = "/home/martin/IdeaProjects/eldif/out/artifacts/ldif_jar/script1.sh";
        String[] commands = {"echo test1", "echo test3"};

        m1.addCommand("script1.sh",commands);
        m1.runScript(url);

    }

    public void addCommand(String filename, String[] command) throws IOException {
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        writer.println("#!/bin/sh");

        for (int i = 0;i<command.length;i++){
            String s = command[i];
            writer.append(s);
            writer.append("\n");
        }
        writer.close();
    }

    public void runScript(String url){
        String cmd = url;

        ProcessBuilder pb = new ProcessBuilder(cmd);
        try
        {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;
            while ( (line = reader.readLine()) != null) {
                System.out.println(line);
            }

            System.out.println("end of script execution");
        }
        catch (IOException e)
        { System.out.print("error");
            e.printStackTrace();
        }
    }
}
