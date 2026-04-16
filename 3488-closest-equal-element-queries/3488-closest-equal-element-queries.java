
class Solution{
    public List<Integer> solveQueries(int nums[],int queries[]){
        Map<Integer,List<Integer>> map=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            List<Integer> temp=map.getOrDefault(nums[i],new ArrayList<>());
            temp.add(i);
            map.put(nums[i],temp);
        }
        for(int no:map.keySet())
        {
            Collections.sort(map.get(no));
        }
       
        List<Integer> res=new ArrayList<>();
        for(int i:queries)
        {
            int no=nums[i];
            if(map.get(no).size()==1)
            {
                res.add(-1);
            }
            else
            {
                List<Integer> temp=map.get(no);
                int ind=Collections.binarySearch(temp,i);
                int l=Integer.MAX_VALUE,r=Integer.MAX_VALUE;
                if(ind==0)
                {
                    int temp1=temp.get(temp.size()-1)-temp.get(ind);
                    int temp2=nums.length-temp.get(temp.size()-1)+temp.get(ind);
                    int temp3=temp.get(1)-temp.get(ind);
                    int temp4=nums.length-temp.get(1)+temp.get(ind);
                    r=Math.min(temp3,temp4);
                      l=Math.min(temp1,temp2);
                }
                else if(ind==temp.size()-1)
                {
                    int temp1=temp.get(ind)-temp.get(ind-1);
                    int temp2=nums.length-temp.get(ind)+temp.get(ind-1);
                    int temp3=temp.get(ind)-temp.get(0);
                    int temp4=nums.length-temp.get(ind)+temp.get(0);
                    l=Math.min(temp1,temp2);
                    r=Math.min(temp3,temp4);
                }
                else
                {
                    int temp1=temp.get(ind)-temp.get(ind-1);
                    int temp2=nums.length-temp.get(ind)+temp.get(ind-1);
                    l=Math.min(temp1,temp2);
                    int temp3=temp.get(ind+1)-temp.get(ind);
                    int temp4=nums.length-temp.get(ind+1)+temp.get(ind);
                    r=Math.min(temp3,temp4);
                }

                res.add(Math.min(l,r));
            }

            
            
        }
        return res;
        
    }

    
}