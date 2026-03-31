class Solution {
    public String generateString(String str1, String str2) {
        StringBuilder sb=new StringBuilder();
        int n=str1.length();
        int m=str2.length();
        int t=n+m-1;
        for(int i=0;i<t;i++){
            sb.append('#');
        }
        boolean[] fixed = new boolean[t];
        for(int i=0;i<n;i++){
            if(str1.charAt(i)=='T'){
                for(int j=0;j<m;j++){
                    fixed[i+j] = true;
                    if(sb.charAt(i+j)=='#' ||sb.charAt(i+j)==str2.charAt(j)){
                        sb.setCharAt((i+j),str2.charAt(j));
                    }
                    else return "";
                }
            }
        }
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i)=='#'){
                sb.setCharAt(i, 'a');
            }
        }
        for(int i=0;i<n;i++){
            if(str1.charAt(i)=='F'){
                boolean match = true;
                for(int j=0;j<m; j++){
                    if(sb.charAt(i+j) != str2.charAt(j)){
                        match = false;
                        break;
                    }
                }
                if(match){
                    boolean changed = false;

                    for(int j=m-1; j>=0;j--){
                        int pos =i+j;
                        if(!fixed[pos]){
                            if(sb.charAt(pos)=='a'){
                                sb.setCharAt(pos,'b');
                            }
                            changed = true;
                            break;
                        }
                    }
                    if(!changed) return "";
                }
            }
        }
        return sb.toString();
    }
}