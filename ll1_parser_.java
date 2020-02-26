import java.util.*;
import java.io.*;

public class parser{
    static char non_terminal[],terminal[];
    static int nt_length,t_length;
    static String grammar[][],first[],follow[];
    public static void main(String[] args){
        String nt,t;
        int i,j,n;
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(“Enter the non-terminals”);
        nt=br.readLine();
        nt_length=nt.length();
        non_terminal=new char[nt_length];
        non_terminal=nt.toCharArray();
        System.out.println(“Enter the terminals”);
        t=br.readLine();
        t_length=t.length();
        terminal=new char[t_length];
        terminal=t.toCharArray();
        System.out.println(“Specify the grammar(Enter $ for epsilon production)”);
        grammar=new String[nt_length][];
            for(i=0;i<nt_length;i++){
                System.out.println(“Enter the number of productions for “+non_terminal[i]);
                n=Integer.parseInt(br.readLine());
                grammar[i]=new String[n];
                System.out.println(“Enter the productions”);
                for(j=0;j<n;j++)
                grammar[i][j]=br.readLine();
            }
            File results_file = new File("results_ritu.txt");   //writing to a file
            FileWriter myWriter = new FileWriter("results_ritu.txt");
        first=new String[nt_length];
        for(i=0;i<nt_length;i++)
        first[i]=first(i);
        System.out.println(“First Set”);
        for(i=0;i<nt_length;i++)
        System.out.println(removeDuplicates(first[i]));
        myWriter.write(removeDuplicates(first[i]));
        follow=new String[nt_length];
        for(i=0;i<nt_length;i++)
        follow[i]=follow(i);
        System.out.println(“Follow Set”);
        for(i=0;i<nt_length;i++)
        System.out.println(removeDuplicates(follow[i]));
        myWriter.write(removeDuplicates(follow[i]));

        }
    static String first(int i){

int j,k,l=0,found=0;
String temp=””,str=””;
for(j=0;j<grammar[i].length;j++) //number of productions
{
for(k=0;k<grammar[i][j].length();k++,found=0) //when nonterminal has epsilon production
{
for(l=0;l<nt_length;l++) //finding nonterminal
{
if(grammar[i][j].charAt(k)==non_terminal[l]) //for nonterminal in first set
{
str=first(l);
if(!(str.length()==1 && str.charAt(0)==’$′)) //when epsilon production is the only nonterminal production
temp=temp+str;
found=1;
break;}}
if(found==1)
{
if(str.contains(“$″)) //here epsilon will lead to next nonterminal’s first set
continue;}
else //if first set includes terminal
temp=temp+grammar[i][j].charAt(k);
break;}}
return temp;}
static String follow(int i)
{
char pro[],chr[];
String temp=””;
int j,k,l,m,n,found=0;
if(i==0)
temp=”$”;
for(j=0;j<nt_length;j++)
{
for(k=0;k<grammar[j].length;k++) //entering grammar matrix
{
pro=new char[grammar[j][k].length()];
pro=grammar[j][k].toCharArray();
for(l=0;l<pro.length;l++) //entering each production
{
if(pro[l]==non_terminal[i]) //finding the nonterminal whose follow set is to be found
{
if(l==pro.length-1) //if it is the last terminal/non-terminal then follow of current non-terminal
{
if(j<i)
temp=temp+follow[j];}
else
{
for(m=0;m<nt_length;m++)
{
if(pro[l+1]==non_terminal[m]) //first of next non-terminal otherwise (else later…)
{
chr=new char[first[m].length()];
chr=first[m].toCharArray();
for(n=0;n<chr.length;n++)
{
if(chr[n]==’9′) //if first includes epsilon
{
if(l+1==pro.length-1)
temp=temp+follow(j); //when non-terminal is second last
else
temp=temp+follow(m);}
else
temp=temp+chr[n]; //include whole first set except epsilon
}
found=1;}}
if(found!=1)
temp=temp+pro[l+1]; //follow set will include terminal(else is here)
}}}}}
return temp;
}
static String removeDuplicates(String str)
{
int i;
char ch;
boolean seen[] = new boolean[256];
StringBuilder sb = new StringBuilder(seen.length);
for(i=0;i<str.length();i++)
{
ch=str.charAt(i);
if (!seen[ch])
{
seen[ch] = true;
sb.append(ch);}}
return sb.toString();
}
   }

