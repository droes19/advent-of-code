import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class day4 {
    public static void main(String[] args) {
        // String input = "abcdef609043";
        //String input = "pqrstuv"; // must be 1048970
        String input = "iwrupvqb";

        boolean loop = true;
        int i = 0;
        while (loop) {
            i++;
            String input2 = input + (i);
            //System.out.println(input2);
            try {
                String res = day4.getMd5(input2);
                //if(res.startsWith("00000")){ //part 1
                //    loop = false;
                //}
                if(res.startsWith("000000")){ //part 2
                    loop = false;
                }
            } catch (NoSuchAlgorithmException e) {
                System.out.println("error, " + e);
            }

        }
        System.out.println(i);

    }

    public static String getMd5(String input) throws NoSuchAlgorithmException {
        // Create an MD5 MessageDigest instance
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Update the digest with your input data
        md.update(input.getBytes());

        // Generate the hash (digest) as a byte array
        byte[] digest = md.digest();

        // Convert bytes to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        // Get complete hashed password in hex format
        String generatedHash = sb.toString();
        //System.out.println("MD5 hash: " + generatedHash);
        return generatedHash;
    }
}
