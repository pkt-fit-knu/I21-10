using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    public static List<Entry> data;
    public static List<String> classNames;
    public static List<String[]> possibleValues;
    class TreeNode
    {
        TreeNode[] children; 
        bool seal=false;
        int infolen;
        bool ans;
        int etalon;
        bool[] free;
        List<Entry> content;
        string targetClass;
        public bool Predict(Entry a)
        {
            if (seal) return ans;
            else
            {
                for (int i = 0; i < possibleValues[etalon].Length; ++i)
                {
                    if (a.description[classNames[etalon]].Equals(possibleValues[etalon][i])) return children[i].Predict(a);
                }
            }
            return true;
        }
        public TreeNode(List<Entry> info, bool[] free)
        {
            
            content = info;
            this.free = free;
            if (tryToSeal()) return;
            double[] entropy=new double[classNames.Count];
            etalon = 0;
            double etalonval=0;
            for(int i=0;i<classNames.Count;++i)
            {
                if(!free[i]) continue;
                double temp=countEntropy(i);
                //Console.WriteLine(temp);
                if(temp>etalonval)
                {
                    etalonval = temp;
                    etalon = i;
                }
            }
            children=new TreeNode[possibleValues[etalon].Length];
            GenerateChildren(etalon);
        }
        public void GenerateChildren(int a)
        {
            bool[] newFree =new bool[free.Length];
            for(int i=0;i<newFree.Length;++i)
            {
                newFree[i] = free[i];
            }
            newFree[a] = false;
            List<Entry>[] childinfo = new List<Entry>[possibleValues[etalon].Length];
            for (int i = 0; i < possibleValues[etalon].Length;++i )
            {
                childinfo[i] = new List<Entry>();
            }
            for (int i = 0; i < possibleValues[etalon].Length;++i )
            {
                foreach (Entry q in content)
                {
                    if (q.description[classNames[etalon]].Equals(possibleValues[etalon][i])) childinfo[i].Add(q);
                }
            }
            //Console.WriteLine("generating yes");
            for (int i = 0; i < possibleValues[etalon].Length; ++i)
            {
                children[i] = new TreeNode(childinfo[i], newFree);
            }

        }
        public void outputTree(int spaces)
        {
            
            for(int i=0;i<spaces;++i)
            {
                Console.Write(" ");
            }
            if (seal)
            {
                Console.WriteLine("SEALED "+ ans);
                return;
            }
            Console.WriteLine(classNames[etalon]);
            
            for (int i = 0; i < possibleValues[etalon].Length; ++i)
            {
                children[i].outputTree(spaces+2);
            }
        }
        public double countEntropy(int target)
        {
            double ans = 0;
            foreach(String u in possibleValues[target])
            {
                int count=0;
                foreach(Entry q in content)
                {
                    if (q.description[classNames[target]].Equals(u)) count++;
                }
                if(count>0)
                {
                    ans -= count * Math.Log10((double)count / (double)content.Count);
                }
                

            }
            return ans;
        }
        public void printEntries()
        {
            foreach(Entry u in content)
            {
                u.printEntry();
            }
        }
        public bool tryToSeal()
        {
            if(content.Count==0)
            {
                seal = true;
                ans = true;
                return true;
            }
           
            bool etalon2 = content.First().value;
            foreach(Entry u in content)
            {
                if (u.value != etalon2)
                {
                    bool pass = false;
                    for (int i = 0; i < free.Length; ++i)
                    {
                        if (free[i]) pass = true;
                    }
                    if (!pass)
                    {
                        seal = true;
                        ans = true;
                        //Console.WriteLine("Sealed cause no more freedom");
                        return true;
                    }
                    return false;
                }
            }
            //Console.WriteLine("Sealed cause same "+ etalon2);
            //printEntries();
            seal = true;
            ans = etalon2;
            return true;
        }
    }
    public class Entry
    {
        public bool value;
        public Dictionary<String, String> description;
        public Entry(bool value, List<String> className, String[] classVal)
        {
            this.value=value;
            description=new Dictionary<String,String>();
            for(int i=0;i<className.Count;++i)
            {
                description.Add(className[i], classVal[i]);
            }
        }
        public void printEntry()
        {
            Console.Write(value);
            foreach(String s in classNames)
            {
                Console.Write(" "+description[s]);
            }
            Console.WriteLine();
        }
    }
    
    static void Main(string[] args)
    {
        data = new List<Entry>();
        classNames = new List<String>();
        possibleValues = new List<String[]>();
        classNames.Add("Outlook");
        possibleValues.Add(new String[]{"Sunny" , "Overcast", "Rainy"});
        classNames.Add("Temperature");
        possibleValues.Add(new String[] { "cool", "mild", "hot" });
        classNames.Add("Humidity");
        possibleValues.Add(new String[] { "normal", "high" });
        
        
        classNames.Add("Windy");
        possibleValues.Add(new String[] { "false", "true" });
        String[] lines = System.IO.File.ReadAllLines(@"C:\Users\Grole\Desktop\iris\weather.txt");
        String[] w;
        foreach(String line in lines)
        {
            w = line.Split(',');
            if (w[4].Equals("yes"))
            {
                data.Add(new Entry(true, classNames, w));
            }
            else
            {
                data.Add(new Entry(false, classNames, w));
            }
        }
        bool[] free = { true, true, true, true };
        TreeNode t = new TreeNode(data, free);
        
        t.outputTree(0);
        foreach(Entry q in data)
        {
            Console.WriteLine(t.Predict(q));
        }
    }
}
