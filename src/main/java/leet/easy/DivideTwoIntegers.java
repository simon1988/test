package leet.easy;



/**
 * Divide two integers without using multiplication, division and mod operator.
 */

public class DivideTwoIntegers {
	public int divide(int dividend, int divisor) {
        if(divisor==0)return Integer.MAX_VALUE;
        boolean isNeg = (dividend<0&&divisor>0)||(dividend>0&&divisor<0);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        
        int digit=0;
        int res=0;
        while(divisor<=(dividend>>1)){
            divisor<<=1;
            digit++;
        }
        while(digit>=0){
            if(dividend>divisor){
                dividend-=divisor;
                res+=1<<digit;
            }
            divisor>>=1;
            digit--;
        }
        return isNeg?0-res:res;
    }
	public double pow(double x, int n) {
		if (n == 0)
			return 1;
		else if (n % 2 == 0) {
			double d = pow(x, n / 2);
			return d * d;
		} else if (n > 0) {
			double d = pow(x, (n - 1) / 2);
			return d * d * x;
		} else {
			double d = pow(x, (n + 1) / 2);
			return d * d / x;
		}
	}
	public double sqrt(int x) {  
	    if (x == 0) return 0;  
	    double lastY = 0;  
	    double y = 1;  
	    while (y != lastY)  
	    {  
	        lastY = y;  
	        y = (y + x / y) / 2;  
	    }  
	    return y;  
	}
	public String multiply(String num1, String num2) {
		int length1 = num1.length();
		int length2 = num2.length();
		int[] m = new int[length1 + length2];
		for (int k = length2 - 1, offset2 = 0; k >= 0; k--, offset2++) {
			for (int i = length1 - 1, offset1 = 0; i >= 0; i--, offset1++) {
				m[length1 + length2 - 1 - offset1 - offset2] += (num1.charAt(i) - '0')
						* (num2.charAt(k) - '0');
			}
		}
		int add = 0;
		for (int t = length1 + length2 - 1; t >= 0; t--) {
			int value = m[t] + add;
			add = value / 10;
			m[t] = value % 10;
		}
		StringBuffer sb = new StringBuffer();
		int w = 0;
		for (; w < length1 + length2; w++) {
			if (m[w] != 0)
				break;
		}
		for (int e = w; e < length1 + length2; e++) {
			sb.append(m[e]);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}
}