/* ************************************************************************* *\
*                Programmierung 1 HS 2016 - Serie 2-1                         * 
\* ************************************************************************* */

// Lorenzo Wipfli 13-933-262
// Michael Senn, 16-126-880

import java.text.DecimalFormat;
import java.util.Random;

public class RandomISBN
{
	private static final String FORMAT_TWO_DIGITS = "00";
	private static final String FORMAT_THREE_DIGITS = "000";

	public static void main( String args[] )
	{
		System.out.println("1st ISBN: " + makeISBN());
		System.out.println("2nd ISBN: " + makeISBN());
		System.out.println("3rd ISBN: " + makeISBN());
	}

	/** generates and returns a random ISBN in the format XX-XXX-XX-C */
	public static String makeISBN()
	{
		// Do NOT change the declaration of the following variables!
		String laendercode;
		String bandnr;
		String verlagsnr;
		String checksum;

		// Much more convenient than Math.rand() - see below.
		Random rnd = new Random();

		DecimalFormat formatTwoDigits   = new DecimalFormat(FORMAT_TWO_DIGITS);
		DecimalFormat formatThreeDigits = new DecimalFormat(FORMAT_THREE_DIGITS);

		// Random numbers from 1-99: (int)[0, 99[ + 1 = (int)[1, 99]
		int laenderCodeNumeric = rnd.nextInt(99) + 1;
		int verlagsNrNumeric   = rnd.nextInt(99) + 1;
		// Random number from 100-999: (int)[0, 900[ + 100 = (int)[100, 999]
		int bandNrNumeric      = rnd.nextInt(900) + 100;

		laendercode = formatTwoDigits.format(laenderCodeNumeric);
		bandnr      = formatThreeDigits.format(bandNrNumeric);
		verlagsnr   = formatTwoDigits.format(verlagsNrNumeric);

		checksum = calculateControlNumber(laendercode, bandnr, verlagsnr).toString();

		// Do not change the following line
		return laendercode + "-" + bandnr + "-" + verlagsnr + "-" + checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp( int i )
	{
		// Do NOT change this method!
		int doubled = 2 * i;
		if ( doubled >= 10 ) {
			doubled = doubled - 9;
		}
		return doubled;
	}

	private static Integer calculateControlNumber(String laenderCode, String bandNr, String verlagsNr) {
		Integer checksum;

		int l1 = Integer.parseInt(laenderCode.substring(0, 1));
		int l2 = Integer.parseInt(laenderCode.substring(1, 2));

		int b1 = Integer.parseInt(bandNr.substring(0, 1));
		int b2 = Integer.parseInt(bandNr.substring(1, 2));
		int b3 = Integer.parseInt(bandNr.substring(2, 3));

		int v1 = Integer.parseInt(verlagsNr.substring(0, 1));
		int v2 = Integer.parseInt(verlagsNr.substring(1, 2));

		checksum = (hashOp(l1) + l2 + hashOp(b1) + b2 + hashOp(b3) + v1 + hashOp(v2)) % 10;

		return checksum;
	}
}
