
public class SnelheidOefening {

	/**
	 * @param args
	 */
	public static void main( String[] args){
		GetalRij gr = new GetalRij(100000, 200000);
        System.out.println(gr.zitErinD(150000));
        System.out.println(tijd());
	}

	// Hulpmethode voor tijdsbepaling
	private static long tijd(){
		return System.currentTimeMillis();
	}

}
