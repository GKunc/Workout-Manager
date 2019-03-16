package BackEnd;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://flow.polar.com/oauth2/authorization?response_type=code&client_id=6e17aaf4-e748-411f-abe9-416435751215&redirect_uri=http://localhost

public class getSummary {

    public static final String USER_ID = "6e17aaf4-e748-411f-abe9-416435751215";
    public static final String USER_SECRET = "6d315e44-4bb3-41b6-bcb6-4866a5261b85";
    public static final String AUTHORIZATION_URL = "https://flow.polar.com/oauth2/authorization";
    public static final String ACCESS_TOKEN_URL = "https://polarremote.com/v2/oauth2/token";
    public static final String ACCESSLINK_URL = "https://www.polaraccesslink.com/v3";
    public static final String CALLBACK_URL = "http://127.0.0.1:8080/Callback";
    public static final String CODE = "9fd692a2a7e873df1ec2497c8553926e";
    public static final String REFERER = "https://flow.polar.com/login?n=D6FQQAAAAAAAAAAACXDM2CSAIAIABYDX3GB5XJTBP5IPEIESYYNNTUUOLAD6JXLR7H5G4EKE2WFJJ4MIOOLP54XGF6GJ4Q5T2G7HFWFJR7TUVNPDSEJLO6AKWH3WG3IIFTRKIJCZKVEAKMCJDSUPYZSUV2WYMDFU5CPBOIZJBGZGCAAAAA%3D%3D%3D%3D%3D%3D";
    private static List<String> cookies;
    private static HttpsURLConnection conn;
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:65.0) Gecko/20100101 Firefox/65.0";

    private static String getPageContent(String url) throws Exception {

        URL obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();

        // default is GET
        conn.setRequestMethod("GET");

        conn.setUseCaches(false);

        // act like a browser
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        conn.setRequestProperty("Host", "flow.polar.com");
        if (cookies != null) {
            for (String cookie : cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        in.close();

        // Get the response cookies
        setCookies(conn.getHeaderFields().get("Set-Cookie"));

        return response.toString();

    }

    public static String getFormParams(String html, String username, String password)
            throws UnsupportedEncodingException {

        System.out.println("Extracting form's data...");

        Document doc = Jsoup.parse(html);

        // Google form id
        Element loginform = doc.getElementById("loginForm");

        Elements inputElements = loginform.getElementsByTag("input");
        List<String> paramList = new ArrayList<String>();
        for (Element inputElement : inputElements) {
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");

            if (key.equals("email"))
                value = username;
            else if (key.equals("password"))
                value = password;
            else
                continue;
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));

           // System.out.println("INPUT: " + inputElement + "\n");
        }

        // build parameters list
        StringBuilder result = new StringBuilder();
        for (String param : paramList) {
            if (result.length() == 0) {
                result.append(param);
            } else {
                result.append("&" + param);
            }
        }
        System.out.println(result);
        return result.toString();
    }

    private static String sendPost(String url, String postParams) throws Exception {


        URL obj = new URL(url);
        HttpsURLConnection conn1 = (HttpsURLConnection) obj.openConnection();
    // ------------------------------------------------------------
        Connection.Response response1 =
                Jsoup.connect("https://flow.polar.com/login?n=D6FQQAAAAAAAAAAACXDM2CSAIAIABYDX3GB5XJTBP5IPEIESYYNNTUUOLAD6JXLR7H5G4EKE2WFJJ4MIOOLP54XGF6GJ4Q5T2G7HFWFJR7TUVNPDSEJLO6AKWH3WG3IIFTRKIJCZKVEAKMCJDSUPYZSUV2WYMDFU5CPBOIZJBGZGCAAAAA%3D%3D%3D%3D%3D%3D")
                        .userAgent(USER_AGENT)
                        .timeout(10 * 1000)
                        .method(Method.POST)
                        .data("email", "gregson0307@gmail.com")
                        .data("password", "19Kunc97")
                        .data("returnUrl", "/?n=D6FQQAAAAAAAAAAACXDM2CSAIAIABYDX3GB5XJTBP5IPEIESYYNNTUUOLAD6JXLR7H5G4EKE2WFJJ4MIOOLP54XGF6GJ4Q5T2G7HFWFJR7TUVNPDSEJLO6AKWH3WG3IIFTRKIJCZKVEAKMCJDSUPYZSUV2WYMDFU5CPBOIZJBGZGCAAAAA%3D%3D%3D%3D%3D%3D")
                        .followRedirects(true)
                        .execute();

        // todo !!! im here now i need to accept conditions !!!
        // todo !!! checkbox and submit !!!
        //parse the document from response
        Document doc = Jsoup.connect("http://www.kurshtml.edu.pl/html/pole_wyboru,formularze.html").get();
        Elements all = doc.getElementsByTag("input");

        for(Element q: all) {
            if(q.attr("type").equalsIgnoreCase("checkbox"))
                    System.out.println("CHECKBOX: " + q);
        }

        Document document = response1.parse();

        Element campaignForm = document.body();
        Elements allInputFields = campaignForm.getElementsByTag("input");

        System.out.println(document);
        Connection.Response response2 =
                Jsoup.connect(document.location())
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Iridium/58.0 Safari/537.36 Chrome/58.0.3029.81")
                        .timeout(10 * 1000)
                        .method(Method.POST)
                        .data("icicle", "XUIIASMXH6QQDSDHWLUS6XUJFTMEG6X6WZRACOCP4GR33HPUES2WCIJCGTIOCOC4SIXQ33PM4PV6Y7TD6JMND2WLD2YYJCKSQKODKKONOISDBB6WZG5P7UUIYLVYNX67VWK7PH5WMZEH6VDXBYMU2ONQXRS45NCJ6WOWUZ7YT7WRZ2ZE274WOQHI3ARQUP4Z474V4PCNICQIGQX7FOYRYJYLFFUHJVPL2KWHJIXSXZYR4J4PUZCGHRKVCPLLXRHIU4GLE3F2YGJRJD4WKFTUZZ2VP4======")
                        .followRedirects(true) // go to localhost
                        .execute();
        //System.out.println("RESPONSE" + httpResponse);
        // ------------------------------------------------------------
        Document document2 = response2.parse();
        //System.out.println(document2.location());
        Document pageCode = Jsoup.connect("http://127.0.0.1:5000/oauth2_callback").get();
        System.out.println(pageCode.body());

        return new String("sendPOST");

    }

    public static String getHTML(String url) throws Exception {

        HttpURLConnection con = (HttpURLConnection)(new URL( url ).openConnection()); // go to auth page
        con.setInstanceFollowRedirects( false );
        con.connect();
        int responseCode = con.getResponseCode();
        System.out.println( responseCode );
        String location = con.getHeaderField( "Location" ); // redirect url
        System.out.println("LOCATION -> " + location);
        String newURL = "https://flow.polar.com" + location; // go to login page

        HttpURLConnection con1 = (HttpURLConnection)(new URL( newURL ).openConnection());
        con1.setInstanceFollowRedirects( true );
        con1.connect();
        int responseCode1 = con1.getResponseCode();
        System.out.println( responseCode1 );

        String login = "gregson0307@gmail.com";
        String password = "19Kunc97";

        String page = getPageContent(newURL);
        String postParams = getFormParams(page, login, password);
        String codeURL = sendPost(newURL, postParams); // zle przekierowuje!!!

        System.out.println("codeURL: " + codeURL);

        return new String( "Koniec" ); // change that

    }

    public static void setCookies(List<String> _cookies) {
        cookies = _cookies;
    }
    public static void main(String args[]) throws Exception {
        System.out.println(getHTML("https://flow.polar.com/oauth2/authorization?response_type=code&client_id=6e17aaf4-e748-411f-abe9-416435751215"));
    }
}