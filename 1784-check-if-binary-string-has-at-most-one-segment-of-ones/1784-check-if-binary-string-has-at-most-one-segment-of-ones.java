class Solution {
    public boolean checkOnesSegment(String s) {
        boolean flag=false;
        for(int i=0;i<s.length()-1;i++)
        {
            if(s.charAt(i)=='1' && s.charAt(i+1)=='1')
            {
                continue;
            }
            else if(s.charAt(i)=='1' && s.charAt(i+1)=='0')
            {
                flag=true;
            }
            else if(s.charAt(i)=='0' && s.charAt(i+1)=='1')
            {
                if(flag)
                {
                    return false;
                }
            }
        }

        return true;
    }
}