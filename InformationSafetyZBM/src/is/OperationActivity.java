package is;

public class OperationActivity {
	public static byte[] mergeBytes(byte[] former, byte[] later){
		byte[] res = new byte[former.length+later.length];
		for(int i = 0; i < former.length; i++){
			res[i] = former[i];
		}
		for(int i = 0; i < later.length; i++){
			res[i+former.length] = later[i];
		}
		return res;
	}
	
	public static byte[] cutBytes(byte[] bytes, int start, int end){
		byte[] res = new byte[end-start];
		for(int i = 0; i < end-start; i++){
			res[i] = bytes[i+start];
		}
		return res;
	}
	
	public static int byteArrayToIntBeforeReverse(byte[] b){
		byte t = 0;
		for(int i = 0; i < b.length/2; i++){
			t = b[i];
			b[i] = b[b.length - 1 - i];
			b[b.length - 1 - i] = t;
		}
		return byteArrayToInt(b);
	}
	
	public static int byteArrayToInt(byte[] b) {
		switch(b.length){
		case 4: return   b[3] & 0xFF |
	            (b[2] & 0xFF) << 8 |
	            (b[1] & 0xFF) << 16 |
	            (b[0] & 0xFF) << 24;
		case 2: return 
	            (b[1] & 0xFF) |
	            (b[0] & 0xFF) << 8;
		case 1: return b[0] & 0xFF;
		}
		return 0;
	}
}

