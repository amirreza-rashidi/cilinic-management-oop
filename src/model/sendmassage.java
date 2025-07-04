
/*


package
        model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class sendmassage {

    private static final String API_URL = "";
    private static final String FROM_NUMBER = "50002710074252"; // شماره اختصاصی شما

    public static boolean sendSMS(String to, String patientName, String doctorName, String appointmentDate, String appointmentTime) {
        try {

            String text = String.format(
                    "بیمار گرامی %s\nنوبت شما با دکتر %s ثبت شد.\nتاریخ نوبت: %s\nزمان نوبت: %s\nلطفاً در این تاریخ و زمان به کلینیک مراجعه فرمایید.",
                    patientName, doctorName, appointmentDate, appointmentTime
            );


            String escapedText = escapeJsonString(text);
            String escapedTo = escapeJsonString(to);
            String payload = String.format("{\"from\":\"%s\", \"to\":\"%s\", \"text\":\"%s\"}",
                    FROM_NUMBER, escapedTo, escapedText);

            System.out.println("Sending payload: " + payload);


            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);


            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    (responseCode >= 200 && responseCode < 300) ? conn.getInputStream() : conn.getErrorStream(),
                    StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }

            String responseBody = response.toString();
            System.out.println("Response Body: " + responseBody);


            return responseBody.contains("\"recId\"") || responseBody.contains("\"status\":1");

        } catch (Exception e) {
            System.out.println("Exception in sending SMS: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    private static String escapeJsonString(String input) {
        if (input == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }

}*/