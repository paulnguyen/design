
import java.io.*;
import java.security.*;
import java.security.spec.*;

class VerSig {

    public static void main(String[] args) {

        /* Verify a DSA signature */

        if (args.length != 3) {
            System.out.println("Usage: VerSig " +
                "publickeyfile signaturefile " + "datafile");
        }
        else try {

        	// Read in the encoded public key bytes
		FileInputStream keyfis = new FileInputStream(args[0]);
		byte[] encKey = new byte[keyfis.available()];  
		keyfis.read(encKey);
		keyfis.close();

		// Generate Public Key from Spec
		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
		KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
		PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

		// Read in the Signature Bytes
		FileInputStream sigfis = new FileInputStream(args[1]);
		byte[] sigToVerify = new byte[sigfis.available()]; 
		sigfis.read(sigToVerify);
		sigfis.close();

		// Initialize the Signature Object for Verification
		Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
		sig.initVerify(pubKey);
		
		// Supply the Signature Object With the Data to be Verified
		FileInputStream datafis = new FileInputStream(args[2]);
		BufferedInputStream bufin = new BufferedInputStream(datafis);
		byte[] buffer = new byte[1024];
		int len;
		while (bufin.available() != 0) {
    			len = bufin.read(buffer);
    			sig.update(buffer, 0, len);
		};
		bufin.close();

		// Verify the Signature
		boolean verifies = sig.verify(sigToVerify);
		System.out.println("signature verifies: " + verifies);

        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }
    }

}

