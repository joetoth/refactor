package com.github.joetoth.refactor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> exec(String cmd, File workingDir) {
        return exec(cmd, new String[]{}, workingDir);
    }

    public static List<String> exec(String cmd, String[] envp, File workingDir) {
        List<String> o = new ArrayList<String>();
        try {
            //Run macro on target
            ProcessBuilder pb = new ProcessBuilder(new String[]{"bash", "-c", cmd});
            pb.directory(workingDir);
            pb.redirectErrorStream(true);
            Process p = pb.start();

            //      Process p = Runtime.getRuntime().exec(cmd, envp, workingDir);
            p.waitFor();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                o.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

}
