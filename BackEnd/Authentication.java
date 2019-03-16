package BackEnd;

import javax.naming.AuthenticationException;
import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.Thread.sleep;

public class Authentication {
    Authentication() throws IOException, InterruptedException {
        // todo change to read from file
        String url = "https://flow.polar.com/oauth2/authorization?response_type=code&client_id=6e17aaf4-e748-411f-abe9-416435751215";

        String[]callAndArgs= {"python","./python/authorization.py"};
        Process p = Runtime.getRuntime().exec(callAndArgs);
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));

        String s = "";
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }

        String[]callAndArgs1= {"python","./python/accesslink_example.py"};
        Process p1 = Runtime.getRuntime().exec(callAndArgs1);

        BufferedReader stdInput1 = new BufferedReader(new
                InputStreamReader(p1.getInputStream()));

        BufferedReader stdError1 = new BufferedReader(new
                InputStreamReader(p1.getErrorStream()));

        String s1 = "";
        while ((s1 = stdInput1.readLine()) != null) {
            System.out.println(s1);
        }

        while ((s1 = stdError1.readLine()) != null) {
            System.out.println(s1);
        }

    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Authentication auth = new Authentication();

    }
}